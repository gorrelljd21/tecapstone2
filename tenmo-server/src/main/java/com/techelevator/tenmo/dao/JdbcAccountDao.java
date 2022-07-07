package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;
    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }
    private List<Account> accounts = new ArrayList<>();
    //TODO figure out the from and to userID situation, should we use account_id?
    //TODO how to use postman to test
    public int transfer(int fromUserId, int toUserId, BigDecimal transferredMoney){
        String transferSql =
                    "begin transaction;" +
                    " update account set balance = balance - ? where user_id = ?;" +
                    " update account set balance = balance + ? where user_id = ?;" +
                    // need insert to and returns
                    " commit";
        SqlRowSet result = jdbcTemplate.queryForRowSet(transferSql, transferredMoney, fromUserId, transferredMoney, toUserId);
        if(result.next()){
            return result.getBigDecimal("balance");
        }
        throw new AccountNotFoundException();
    }
    //To get all users
    public List<Account> listOfAccounts(int accountId){

        String getUsersSql =
                "select user_id, username from tenmo_user";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getUsersSql);
        while (results.next()){
            accounts.add(mapToRowUsers(results));
        }
        return accounts;
    }
    // To get a single account
    public Account get(int accountId) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    public Account mapToRowUsers(SqlRowSet result){
        Account account = new Account();

        account.setAccountId(result.getInt("account_id"));

        return account;
    }

}
