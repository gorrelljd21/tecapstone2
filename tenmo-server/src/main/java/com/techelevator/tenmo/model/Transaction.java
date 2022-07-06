package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Transaction {

    private int id;
    @NotBlank(message = "UserId can't be blank.")
    private int userId;

    @NotBlank(message = "AccountId can't be blank.")
    private int accountId;

    @NotBlank(message = "TransferredMoney must have a positive value.")
    private BigDecimal transferredMoney;

    @NotBlank (message = "Balance must be >= 0.")
    private BigDecimal balance;

    public Transaction(){}

    public Transaction(int userId, int accountId, BigDecimal transferredMoney, BigDecimal balance) {
        this.userId = userId;
        this.accountId = accountId;
        this.transferredMoney = transferredMoney;
        this.balance = balance;
    }

    public Transaction(int id, int userId, int accountId, BigDecimal transferredMoney, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.accountId = accountId;
        this.transferredMoney = transferredMoney;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTransferredMoney() {
        return transferredMoney;
    }

    public void setTransferredMoney(BigDecimal transferredMoney) {
        this.transferredMoney = transferredMoney;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return id == transaction.id && userId == transaction.userId &&
                accountId == transaction.accountId &&
                transferredMoney == transaction.transferredMoney;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "Transaction{" + "id= " + id
                + ", userId= " + userId
                + ", accountId= " + accountId
                + ", transferredMoney= " + transferredMoney
                + "}";
    }
}


