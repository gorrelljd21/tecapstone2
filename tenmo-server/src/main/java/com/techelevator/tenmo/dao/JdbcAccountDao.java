package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO figure out the from and to userID situation, should we use account_id?
    //TODO how to use postman to test
    public BigDecimal transfer(int fromUserId, int toUserId, BigDecimal transferredMoney){
        String transferSql =
                    "begin transaction;" +
                    " update account set balance = balance - ? where user_id = ?;" +
                    " update account set balance = balance + ? where user_id = ?;" +
                    " commit";
        SqlRowSet result = jdbcTemplate.queryForRowSet(String.valueOf(transferredMoney), fromUserId, toUserId);
        if(result.next()){
            return result.getBigDecimal("balance");
        }
        throw new AccountNotFoundException();
    }

    public List<User> listOfUsers(int userId){
        List<User> users = new ArrayList<>();
        String getUsersSql =
                "select user_id, username from tenmo_user";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getUsersSql);
        while (results.next()){
            users.add(mapToRowUsers(results));
        }
        return users;
    }

    public User mapToRowUsers(SqlRowSet result){
        User user = new User();

        user.setId(result.getLong("user_id"));
        user.setUsername(result.getString("username"));

        return user;
    }

}
