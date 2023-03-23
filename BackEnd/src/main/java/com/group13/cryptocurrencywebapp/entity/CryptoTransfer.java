package com.group13.cryptocurrencywebapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_transfer")
public class CryptoTransfer extends Transaction {

    private String exchangeReferenceId;
    private String transactionType;

    public CryptoTransfer() {
        super();
    }

    public CryptoTransfer(int transactionId, String currency, float amount, LocalDateTime time,
            float final_amount, String exchangeReferenceId, String transactionType) {
        super(transactionId, currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
        this.transactionType = transactionType;
    }

    public CryptoTransfer(String currency, float amount, LocalDateTime time, float final_amount,
            String exchangeReferenceId, String transactionType) {
        super(currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
        this.transactionType = transactionType;
    }

    public String getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(String exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}
