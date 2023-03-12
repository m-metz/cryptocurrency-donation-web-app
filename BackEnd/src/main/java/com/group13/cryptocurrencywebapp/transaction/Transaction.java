package com.group13.cryptocurrencywebapp.transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group13.cryptocurrencywebapp.fee.Fee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_receipt_id")
    private int id;

    private String currency;

    private float amount;

    private LocalDateTime time;

    private float final_amount;

    private List<Fee> fees;

    @JsonIgnore
    @OneToMany(mappedBy = "transaction")
    private List<Fee> baseMoonResearchers = new ArrayList<>();

    public Transaction(int id, String currency, float amount, LocalDateTime time,
            float final_amount) {
        this.id = id;
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

}
