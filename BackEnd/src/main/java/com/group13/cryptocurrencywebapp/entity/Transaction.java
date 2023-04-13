package com.group13.cryptocurrencywebapp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/** 
 * <pre>
 * Class Name: Transaction
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>Entity class created to hold information about any kind of transfer that occurs during the donation process
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    private String currency;

    private float amount;

    private LocalDateTime time;

    private float final_amount;

    // @JsonIgnore
    @OneToMany(mappedBy = "transaction")
    private List<Fee> fees = new ArrayList<>();

    public Transaction() {
    }

    public Transaction(int transactionId, String currency, float amount, LocalDateTime time,
            float final_amount) {
        this.transactionId = transactionId;
        this.currency = currency;
        this.amount = amount;
        this.time = time;
        this.final_amount = final_amount;
    }
    

    public Transaction(String currency, float amount, LocalDateTime time, float final_amount) {
        this.currency = currency;
        this.amount = amount;
        this.time = time;
        this.final_amount = final_amount;
    }

    // public Transaction(String currency, float amount, LocalDateTime time,
    //         float final_amount, List<Fee> fees) {
    //     this.currency = currency;
    //     this.amount = amount;
    //     this.time = time;
    //     this.final_amount = final_amount;
    //     this.fees = fees;
    // }

    // public Transaction(int transactionId, String currency, float amount, LocalDateTime time,
    //         float final_amount, List<Fee> fees) {
    //     this.transactionId = transactionId;
    //     this.currency = currency;
    //     this.amount = amount;
    //     this.time = time;
    //     this.final_amount = final_amount;
    //     this.fees = fees;
    // }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public float getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(float final_amount) {
        this.final_amount = final_amount;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public void addFee(Fee fee){
        fees.add(fee);
        fee.setTransaction(this);
    }

    public void removeFee(Fee fee) {
        fees.remove(fee);
        fee.setTransaction(null);
    }

}
