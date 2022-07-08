package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> listOfAccounts (int accountId) throws AccountNotFoundException;

    BigDecimal getBalanceByUsername(String username) throws AccountNotFoundException;
}
// need transfer method