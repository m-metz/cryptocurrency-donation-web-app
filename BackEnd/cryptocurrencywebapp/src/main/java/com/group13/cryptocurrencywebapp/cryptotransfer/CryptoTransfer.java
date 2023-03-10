package com.group13.cryptocurrencywebapp.cryptotransfer;

import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_transfer")
public class CryptoTransfer extends Transaction {

    private int exchangeReferenceId;
    private String transactionType;

}