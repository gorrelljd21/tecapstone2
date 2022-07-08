package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface TransactionDao {

    BigDecimal getBalance(int accountId) throws AccountNotFoundException;

    int getTransaction(int transaction_id) throws TransactionNotFoundException;

    int transfer(Transaction transaction) throws TransactionNotFoundException;

    Transaction showTransfersById(int id) throws TransactionNotFoundException;

}

//    public static List<User> users = new ArrayList<>();

//    public TransactionDao() {
//        if (users.size() == 0) {
//            set.Transactions
//        }
//    }
