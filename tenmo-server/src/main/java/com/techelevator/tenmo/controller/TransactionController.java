package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.*;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransactionController {

    private TransactionDao transactionDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao, UserDao userDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    //I need to be able to see my Account Balance.
    //return object instead of primitive
    @GetMapping(path = "/getBalance")
    public BigDecimal getBalance(Principal principal) throws AccountNotFoundException {
        return transactionDao.getBalance(userDao.findIdByUsername(principal.getName()));
    }

    //I need to be able to send a transfer of a specific amount of TE Bucks to a registered user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transaction/makeTransfer")
    public int transfer(@RequestBody @Valid Transaction transaction, Principal principal)
            throws TransactionNotFoundException, InsufficientFundsException, FromUserIdsMatchException, ForbiddenException {

        //must be logged in to make a transfer
        //need to get user name instead of user id findByUsername
        if (userDao.findIdByUsername(principal.getName()) == transaction.getFromUserId()) {
            throw new ForbiddenException();
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

        return transactionDao.transfer(transaction);
    }

    //I need to be able to retrieve the details of any transfer based upon the transfer ID
    @GetMapping(path = "/transaction/{id}")
    public Transaction transactionId(@PathVariable int id, Principal principal)
            throws TransactionNotFoundException, ForbiddenException {

        //get the transaction by id and make sure it belongs to either user
        Transaction finalTransaction = transactionDao.showTransfersById(id);
        int allowedUser = userDao.findIdByUsername(principal.getName());

        if (finalTransaction.getFromUserId() == allowedUser
                || finalTransaction.getToUserId() == allowedUser) {
            return finalTransaction;
        } else {
            throw new ForbiddenException();
        }
    }
}

