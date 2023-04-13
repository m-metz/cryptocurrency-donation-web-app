package com.group13.cryptocurrencywebapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
/** 
 * <pre>
 * Class Name: Trade
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>Entity class created to hold information about a trade from cryptocurrency to fiat currency or stable coin.
 * Extends Transaction
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Entity
@Table(name = "trade")
public class Trade extends Transaction {

    private String exchangeReferenceId;
    private String toCurrency;
    private float convertedAmount;

    public Trade() {
        super();
    }

    public Trade(int transactionId, String currency, float amount, LocalDateTime time, float final_amount,
            String exchangeReferenceId, String toCurrency, float convertedAmount) {
        super(transactionId, currency, amount, time, final_amount);
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

    public String getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(String exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public float getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(float convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

}
