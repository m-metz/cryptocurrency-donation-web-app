package com.group13.cryptocurrencywebapp.trade;

import com.group13.cryptocurrencywebapp.transaction.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trade")
public class Trade extends Transaction {

    private String exchangeReferenceId;

    private String toCurrency;

    private float convertedAmount;

}
