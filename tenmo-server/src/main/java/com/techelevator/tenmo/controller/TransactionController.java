package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("permitAll")
@RestController
public class TransactionController {

    private TransactionDao dao;

    @GetMapping(path = "/transactions/{id}")
    public Transaction get(@PathVariable int id) throws TransactionNotFoundException{
        return dao.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Transaction> list(@RequestParam(defaultValue = "0") int accountId) {
        if (accountId > 0) {
            return dao.listTransaction(accountId);
        }
        return dao.listTransaction();
    }

//    @PutMapping
//    public Transaction increaseBalance(@Valid @RequestBody Transaction transaction, @PathVariable int accountId)
//            throws TransactionNotFoundException{
//        return dao.increaseBalance(transaction, accountId);
//    }

    // changeBalance (userId, accountId, transactionId)
    // newCurrentUserBalance = currentUser - transferAmount
    // newOtherUserBalance = otherUser + transferAmount

    // need to reduce users balance when transfer made to a different user
//
//    @PutMapping
//    public Transaction decreaseBalance(@Valid @RequestBody Transaction transaction, @PathVariable int accountId)
//            throws TransactionNotFoundException{
//        return dao.decreaseBalance(transaction, accountId);
//    }

}
