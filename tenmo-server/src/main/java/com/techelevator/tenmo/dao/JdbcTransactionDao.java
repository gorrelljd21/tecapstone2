package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTransactionDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public String getBalance(int accountId) {
        String transaction = null;
        String balanceSql =
                "select balance from account where account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(balanceSql, accountId);
//        Integer balance = jdbcTemplate.queryForObject(balanceSql, Integer.class, accountId);
        if(result.next()) {
            transaction = String.valueOf(mapRowToTransaction(result));
        }
        return transaction;
//        return getBalance(accountId);
    }
        private Transaction mapRowToTransaction(SqlRowSet result){
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
