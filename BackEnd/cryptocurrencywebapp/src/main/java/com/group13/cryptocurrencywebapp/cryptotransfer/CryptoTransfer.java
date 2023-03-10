package com.group13.cryptocurrencywebapp.cryptotransfer;

import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_transfer")
public class CryptoTransfer extends Transaction{

    private int exchangeReferenceId;
    private String transactionType;

    public int getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(int exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public String getTransactionType() {
        return transactionType;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}