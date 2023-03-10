package com.group13.cryptocurrencywebapp.fee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fee")
public class Fee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    
    private int feeId;
    private float amount;

    public Fee(int feeId, float amount) {
        this.feeId = feeId;
        this.amount = amount;
    }

    public Fee(float amount) {
        this.amount = amount;
    }
    
    
}
