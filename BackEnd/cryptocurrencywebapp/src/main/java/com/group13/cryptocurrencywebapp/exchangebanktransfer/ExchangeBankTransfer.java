package com.group13.cryptocurrencywebapp.exchangebanktransfer;

import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exchange_bank_transfer")
public class ExchangeBankTransfer extends Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_bank_transfer")
    
    private int exchangeReferenceId;

}
