package com.group13.cryptocurrencywebapp.cryptocurrencydonation;    

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_currency_donation")
public class CryptoCurrencyDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private int donationId;

    private int nonProfitId;
    private int donorUserId;
    private int cryptocurrencyTransactionId;

    private String toCryptoAddress;
    private String fromCryptoAddress;


}