package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    //To get all users
    public List<Account> listOfAccounts(int accountId) {
        List<Account> accounts = new ArrayList<>();
        String getUsersSql =
                "select user_id, username from tenmo_user";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getUsersSql);
        while (results.next()) {
            accounts.add(mapToRowUsers(results));
        }
        return accounts;
    }

    // To get a single account
    public Account get(int accountId) throws AccountNotFoundException {
        List<Account> accounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    //I can't send more TE Bucks than I have in my account.
    public BigDecimal getBalanceByUsername(String username) throws AccountNotFoundException {
        String getBalanceByUsernameSql =
                "select balance " +
                        " from account as a" +
                        " join tenmo_user as tu on a.user_id = tu.user_id" +
                        " where username ILIKE ?";
        BigDecimal balanceByUsername = jdbcTemplate.queryForObject(getBalanceByUsernameSql, BigDecimal.class, username);
        return balanceByUsername;
    }


    public Account mapToRowUsers(SqlRowSet result) {
        Account account = new Account();

        account.setAccountId(result.getInt("account_id"));

        return account;
    }

}
