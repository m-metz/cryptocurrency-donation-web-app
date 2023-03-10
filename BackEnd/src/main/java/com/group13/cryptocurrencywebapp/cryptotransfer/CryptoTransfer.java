package com.group13.cryptocurrencywebapp.cryptotransfer;

import java.time.LocalDateTime;
import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_transfer")
public class CryptoTransfer extends Transaction{

    private int exchangeReferenceId;
    private String transactionType;

    public CryptoTransfer(int id, String currency, float amount, LocalDateTime time,
            float final_amount, int exchangeReferenceId, String transactionType) {
        super(id, currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
        this.transactionType = transactionType;
    }

    public CryptoTransfer(String currency, float amount, LocalDateTime time, float final_amount,
            int exchangeReferenceId, String transactionType) {
        super(currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
        this.transactionType = transactionType;
    }

    

    

}
