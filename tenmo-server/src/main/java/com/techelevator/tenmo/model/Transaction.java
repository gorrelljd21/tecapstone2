package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {

    private int id;

//    @NotEmpty(message = "fromUserId cannot be empty.")
    private int fromUserId;

//    @NotEmpty(message = "toUserId cannot be empty.")
    private int toUserId;

    //I can't send a zero or negative amount.
    @Positive(message = "TransferredMoney must have a positive value.")
    private BigDecimal transferredMoney;

//    @NotEmpty(message = "Status cannot be empty.")
    private String status;

    private String fromUsername;

    public Transaction(){}

    public Transaction(int fromUserId, int toUserId, BigDecimal transferredMoney, String fromUsername) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.transferredMoney = transferredMoney;
        this.fromUsername = fromUsername;
    }

    public Transaction(int id, int fromUserId, int toUserId, BigDecimal transferredMoney) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.transferredMoney = transferredMoney;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public BigDecimal getTransferredMoney() {
        return transferredMoney;
    }

    public void setTransferredMoney(BigDecimal transferredMoney) {
        this.transferredMoney = transferredMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && fromUserId == that.fromUserId && toUserId == that.toUserId && Objects.equals(transferredMoney, that.transferredMoney) && Objects.equals(status, that.status) && Objects.equals(fromUsername, that.fromUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromUserId, toUserId, transferredMoney, status, fromUsername);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", transferredMoney=" + transferredMoney +
                ", status='" + status + '\'' +
                ", fromUsername='" + fromUsername + '\'' +
                '}';
    }
}


