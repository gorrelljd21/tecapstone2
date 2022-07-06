package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public class TransactionController {
    private TransactionDao dao;
    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public Transaction addTransaction(@RequestBody @Valid Transaction transfer){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Transaction> list(@RequestParam(defaultValue = "0") int accountId) {
        if (accountId > 0) {
            return dao.listTransaction(accountId);
        }
        return dao.listTransaction();
    }
}
