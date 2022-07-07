package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.http.HttpStatus;
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

    //I need to be able to see my Account Balance.
    @GetMapping(path = "/accounts/{id}")
    public BigDecimal getBalance(@PathVariable int id) throws AccountNotFoundException{
        return dao.getBalance(id);
    }

    //I need to be able to send a transfer of a specific amount of TE Bucks to a registered user
    @PostMapping(path = "/transactions/transfer")
    public int transfer(@RequestBody @Valid Transaction transaction) throws TransactionNotFoundException{
        return dao.transfer(transaction);
    }

    //TODO create error catch for this
    //I must not be allowed to send money to myself.
//    @ResponseStatus(code = HttpStatus.FORBIDDEN)
//    @PostMapping()
    // CATCH(fromUserId != toUserId) maybe







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
