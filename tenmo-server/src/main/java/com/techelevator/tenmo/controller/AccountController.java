package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("permitAll")
@RestController
public class AccountController {

    private AccountDao dao;

    public AccountController(AccountDao accountDao) {
        this.dao = accountDao;
    }
    @GetMapping(path = "/accounts")
    public List<User> listOfUsers(@RequestParam (defaultValue = "0") int userId) throws AccountNotFoundException {
        return dao.listOfUsers(userId);
    }
}

