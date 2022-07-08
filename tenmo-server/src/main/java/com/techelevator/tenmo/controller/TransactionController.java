package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.FromUserIdsMatchException;
import com.techelevator.tenmo.exception.InsufficientFundsException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransactionController {

    private TransactionDao dao;
    private AccountDao accountDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao) {
        this.dao = transactionDao;
        this.accountDao = accountDao;
    }

    //I need to be able to see my Account Balance.
    @GetMapping(path = "/accounts/{id}")
    public BigDecimal getBalance(@PathVariable int id) throws AccountNotFoundException {
        return dao.getBalance(id);
    }

    //I need to be able to send a transfer of a specific amount of TE Bucks to a registered user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transaction/makeTransfer")
    public int transfer(@RequestBody @Valid Transaction transaction, Principal principal)
            throws TransactionNotFoundException, InsufficientFundsException, FromUserIdsMatchException {

        //I can't send more TE Bucks than I have in my account.
        int verifyAmountFromUser = (transaction.getTransferredMoney().compareTo(accountDao.getBalanceByUsername(principal.getName())));

        if (verifyAmountFromUser > 0) {
            throw new InsufficientFundsException();
        }

        if (transaction.getFromUserId() == transaction.getToUserId()) {
            throw new FromUserIdsMatchException();
        }

        System.out.println("Approved.");
        return dao.transfer(transaction);
    }



}
