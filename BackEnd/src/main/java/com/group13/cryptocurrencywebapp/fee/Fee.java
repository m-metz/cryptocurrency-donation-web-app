package com.group13.cryptocurrencywebapp.fee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fee")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")

    private int feeId;
    private float amount;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public Fee(int feeId, float amount) {
        this.feeId = feeId;
        this.amount = amount;
    }

    public Fee(float amount) {
        this.amount = amount;
    }

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

}
