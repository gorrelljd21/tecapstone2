package com.techelevator.tenmo.model;

import org.apache.logging.log4j.message.Message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    @NotBlank(message = "accountId cannot be blank.")
    int accountId;

    @Positive(message = "balance must be a positive value.")
    BigDecimal balance;



    public Account() {}

    public Account(int accountId, BigDecimal balance){
        this.accountId = accountId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
        Account account = (Account) o;
        return accountId == account.accountId && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}
