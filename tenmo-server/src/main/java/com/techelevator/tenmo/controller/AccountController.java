package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("permitAll")
@RestController
public class AccountController {

    private AccountDao dao;

    public AccountController(AccountDao accountDao) {
        this.dao = accountDao;
    }



//    @PostMapping(path = "/accounts")
//    public Transaction transferId(@RequestBody @Valid Transaction transaction){
//
//    }

//    @GetMapping(path = "/accounts")
//    public List<Account> listOfAccounts(@RequestParam(defaultValue = "0") int accountId) throws AccountNotFoundException {
//        return dao.listOfAccounts(accountId);
//    }

//    @GetMapping(path = "/accounts/{userId}")
//    public List<User> getUserId(@PathVariable int userId) throws AccountNotFoundException {
//        return dao.listOfAccounts(userId);
//    }


}

