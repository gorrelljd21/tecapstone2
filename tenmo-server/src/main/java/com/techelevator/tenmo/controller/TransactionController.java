package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("permitAll")
@RestController
public class TransactionController {

    private TransactionDao dao;

    public TransactionController(TransactionDao transactionDao){
        this.dao = transactionDao;
    }

    @GetMapping(path = "/accounts/{id}")
    public BigDecimal getBalance(@PathVariable int id) throws AccountNotFoundException{
        return dao.getBalance(id);
    }

    

    //post that takes fromuser to make sure its legit and covers business rules
    //insert transaction from table to table

//    @RequestMapping(method = RequestMethod.GET)
//    public List<Transaction> list(@RequestParam(defaultValue = "0") int accountId) {
//        if (accountId > 0) {
//            return dao.listTransaction(accountId);
//        }
//        return dao.listTransaction();
//    }

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
