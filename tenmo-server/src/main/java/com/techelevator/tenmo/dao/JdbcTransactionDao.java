package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.AccountNotFoundException;
import com.techelevator.tenmo.exception.TransactionNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal getBalance(int accountId) {
        String balanceSql =
                "select balance from account where account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(balanceSql, accountId);
        if (result.next()) {
            return result.getBigDecimal("balance");
        }
        throw new AccountNotFoundException();
    }

    public int getTransaction(int transaction_id) throws TransactionNotFoundException {
        String transactionSql =
                "select transaction_id from transaction where transaction_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(transactionSql, transaction_id);
        if (result.next()) {
            return result.getInt("transaction_id");
        }
        throw new TransactionNotFoundException();
    }

    //I need to be able to send a transfer of a specific amount of TE Bucks to a registered user.
    public int transfer(Transaction transaction) throws TransactionNotFoundException {
        try {
            String updateSql =
                            " update account set balance = balance - ? where user_id = ?;" +
                            " update account set balance = balance + ? where user_id = ?;";
            jdbcTemplate.update(updateSql, transaction.getTransferredMoney(),
                    transaction.getFromUserId(), transaction.getTransferredMoney(), transaction.getToUserId());

            String transferSql =
                            " insert into transaction (source_user_id, destination_user_id, transfer_amount, status)" +
                            "  values (?, ?, ?, ?) returning transaction_id;";
            Integer result = jdbcTemplate.queryForObject(transferSql, Integer.class,
                    transaction.getFromUserId(), transaction.getToUserId(), transaction.getTransferredMoney(), "Approved");

            return result;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

//    private Transaction mapRowToTransaction(SqlRowSet result) {
//        Transaction transaction = new Transaction();
//        transaction.setFromUserId(result.getInt("fromUserId"));
//        transaction.setToUserId(result.getInt("toUserId"));
//        transaction.setTransferredMoney(result.getBigDecimal("transferred_money"));
//        return transaction;
//    }

