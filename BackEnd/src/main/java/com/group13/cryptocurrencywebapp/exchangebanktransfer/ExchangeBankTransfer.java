package com.group13.cryptocurrencywebapp.exchangebanktransfer;

import java.time.LocalDateTime;
import com.group13.cryptocurrencywebapp.transaction.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "exchange_bank_transfer")
public class ExchangeBankTransfer extends Transaction {

    private int exchangeReferenceId;

    public ExchangeBankTransfer() {
        super();
    }

    public ExchangeBankTransfer(int id, String currency, float amount, LocalDateTime time,
            float final_amount, int exchangeReferenceId) {
        super(id, currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public ExchangeBankTransfer(String currency, float amount, LocalDateTime time,
            float final_amount, int exchangeReferenceId) {
        super(currency, amount, time, final_amount);
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public int getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(int exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }

}
