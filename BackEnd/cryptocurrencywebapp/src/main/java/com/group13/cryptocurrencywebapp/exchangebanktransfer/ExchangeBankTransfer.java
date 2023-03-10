package com.group13.cryptocurrencywebapp.exchangebanktransfer;

import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "exchange_bank_transfer")
public class ExchangeBankTransfer extends Transaction{

    private int exchangeReferenceId;

    public int getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(int exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }

}
