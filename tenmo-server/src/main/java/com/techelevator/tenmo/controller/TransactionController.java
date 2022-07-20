package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.*;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransactionController {

    private TransactionDao dao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao, UserDao userDao) {
        this.dao = transactionDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    //I need to be able to see my Account Balance.
    @GetMapping(path = "/getBalance")
    public BigDecimal getBalance(Principal principal) throws AccountNotFoundException {
        return dao.getBalance(userDao.findIdByUsername(principal.getName()));
    }

    //I need to be able to send a transfer of a specific amount of TE Bucks to a registered user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transaction/makeTransfer")
    public int transfer(@RequestBody @Valid Transaction transaction, Principal principal)
            throws TransactionNotFoundException, InsufficientFundsException, FromUserIdsMatchException, NotLoggedInException {

        //must be logged in to make a transfer
        //need to get user name instead of user id findByUsername
        if (userDao.findByUsername(principal.getName()).equals(transaction.getFromUsername())) {
            throw new NotLoggedInException();
        }

        //I can't send more TE Bucks than I have in my account.
        BigDecimal transferredMoney = transaction.getTransferredMoney();
        BigDecimal currentBalance = accountDao.getBalanceByUsername(principal.getName());

        int verifyAmountFromUser = (transferredMoney.compareTo(currentBalance));

        if (verifyAmountFromUser > 0) {
            throw new InsufficientFundsException();
        }

        if (transaction.getFromUserId() == transaction.getToUserId()) {
            throw new FromUserIdsMatchException();
        }

        return dao.transfer(transaction);
    }

    //I need to be able to retrieve the details of any transfer based upon the transfer ID
    @GetMapping(path = "/transaction/{id}")
    public Transaction transactionId(@PathVariable int id, Principal principal, Transaction transaction)
            throws TransactionNotFoundException, NotLoggedInException {

        String user = toString();

        if (!userDao.findByUsername(principal.getName()).equals(transaction.getFromUsername())) {
            throw new NotLoggedInException();
        }
        return dao.showTransfersById(id);
    }
}

