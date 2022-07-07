package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {

    private int id;

    @Positive(message = "fromUserId can't be negative.")
    private int fromUserId;

    @Positive(message = "toUserId can't be negative.")
    private int toUserId;

    @NotBlank(message = "TransferredMoney must have a positive value.")
    private BigDecimal transferredMoney;

    public Transaction(){}

    public Transaction(int fromUserId, int toUserId, BigDecimal transferredMoney) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.transferredMoney = transferredMoney;
    }

    public Transaction(int id, int fromUserId, int toUserId, BigDecimal transferredMoney, BigDecimal balance) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && fromUserId == that.fromUserId && toUserId == that.toUserId
                && Objects.equals(transferredMoney, that.transferredMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromUserId, toUserId, transferredMoney);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", transferredMoney=" + transferredMoney +
                '}';
    }
}


