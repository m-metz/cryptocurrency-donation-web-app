package com.group13.cryptocurrencywebapp.cryptotransfer;

import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_transfer")
public class CryptoTransfer extends Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crypto_transfer")
    
    private int exchangeReferenceId;
    private String transactionType;

}