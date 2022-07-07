package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal getBalance(int accountId) {
        String transaction = null;
        String balanceSql =
                "select balance from account where account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(balanceSql, accountId);
        if (result.next()) {
            return result.getBigDecimal("balance");
        }
        throw new AccountNotFoundException();
    }

    private Transaction mapRowToTransaction(SqlRowSet result) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(result.getInt("account_id"));
        transaction.setUserId(result.getInt("user_id"));
//        transaction.setTransferredMoney(result.getBigDecimal("transferred_money"));
        transaction.setBalance(result.getBigDecimal("balance"));
        return transaction;
    }

//    private User mapRowToUser(SqlRowSet rs) {
//        User user = new User();
//        user.setId(rs.getLong("user_id"));
//        user.setUsername(rs.getString("username"));
//        user.setPassword(rs.getString("password_hash"));
//        user.setActivated(true);
//        user.setAuthorities("USER");
//        return user;
//    }


}
