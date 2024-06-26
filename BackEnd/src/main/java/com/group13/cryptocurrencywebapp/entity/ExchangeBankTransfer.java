package com.group13.cryptocurrencywebapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/** 
 * <pre>
 * Class Name: ExchangeBankTransfer
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>Entity class created to hold information about a transfer from the exchange to Benevity's bank of choice 
 * (Not used in capstone final project. Intended for future implementation)
 * Extends Transaction entity.
 * 
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
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
