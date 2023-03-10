package com.group13.cryptocurrencywebapp.trade;

import com.group13.cryptocurrencywebapp.transaction.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trade")
public class Trade extends Transaction {

    private String exchangeReferenceId;

    public String getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(String exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public float getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(float convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    private String toCurrency;

    private float convertedAmount;

}
