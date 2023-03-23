package com.group13.cryptocurrencywebapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "exchange_bank_transfer")
public class ExchangeBankTransfer extends Transaction {

    private String exchangeReferenceId;

    public ExchangeBankTransfer() {
        super();
    }

    public ExchangeBankTransfer(int transactionId, String currency, float amount, LocalDateTime time,
            float final_amount, String exchangeReferenceId) {
        super(transactionId, currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public ExchangeBankTransfer(String currency, float amount, LocalDateTime time,
            float final_amount, String exchangeReferenceId) {
        super(currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public String getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(String exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }

}
