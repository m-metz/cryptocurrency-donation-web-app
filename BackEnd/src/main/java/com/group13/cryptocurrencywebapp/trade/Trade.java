package com.group13.cryptocurrencywebapp.trade;

import java.time.LocalDateTime;
import com.group13.cryptocurrencywebapp.transaction.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trade")
public class Trade extends Transaction {

    private String exchangeReferenceId;

    private String toCurrency;

    private float convertedAmount;

    public Trade(int id, String currency, float amount, LocalDateTime time, float final_amount,
            String exchangeReferenceId, String toCurrency, float convertedAmount) {
        super(id, currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
    }

    public Trade(String currency, float amount, LocalDateTime time, float final_amount,
            String exchangeReferenceId, String toCurrency, float convertedAmount) {
        super(currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
    }


    
}