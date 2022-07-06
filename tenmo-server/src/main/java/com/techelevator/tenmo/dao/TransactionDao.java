package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;

import java.util.ArrayList;
import java.util.List;

public interface TransactionDao {

    List<User> list();

    List<Transaction> listTransaction(int accountId);

    List<Transaction> listTransaction();

    Transaction get(int id) throws TransactionNotFoundException;

    Transaction increaseBalance(Transaction transaction, int id) throws TransactionNotFoundException;

    Transaction decreaseBalance(Transaction transaction, int id) throws TransactionNotFoundException;

    Transaction initialStatusApproved(Transaction transaction, int id, int accountId) throws TransactionNotFoundException;

    Transaction seeAccountBalance(Transaction transaction, int id, int accountId) throws AccountNotFoundException;

}

//    public static List<User> users = new ArrayList<>();

//    public TransactionDao() {
//        if (users.size() == 0) {
//            set.Transactions
//        }
//    }
