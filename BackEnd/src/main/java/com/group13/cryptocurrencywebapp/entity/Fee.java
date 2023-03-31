package com.group13.cryptocurrencywebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fee")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private int feeId;

    private float amount;

    private String feeType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transaction_id")

    private Transaction transaction;

    private String currency;

    public Fee() {
    }

    public Fee(int feeId, float amount, Transaction transaction, String feeType, String currency) {
        this.feeId = feeId;
        this.amount = amount;
        this.transaction = transaction;
        this.feeType = feeType;
        this.currency = currency;
    }

    public Fee(float amount, Transaction transaction, String feeType, String currency) {
        this.amount = amount;
        this.transaction = transaction;
        this.feeType = feeType;
        this.currency = currency;
    }

    // public Fee(int feeId, float amount) {
    // this.feeId = feeId;
    // this.amount = amount;
    // }

    // public Fee(float amount) {
    // this.amount = amount;
    // }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
