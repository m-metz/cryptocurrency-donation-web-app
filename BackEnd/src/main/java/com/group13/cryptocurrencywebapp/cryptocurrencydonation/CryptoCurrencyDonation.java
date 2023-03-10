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

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getNonProfitId() {
        return nonProfitId;
    }

    public void setNonProfitId(int nonProfitId) {
        this.nonProfitId = nonProfitId;
    }

    public int getDonorUserId() {
        return donorUserId;
    }

    public void setDonorUserId(int donorUserId) {
        this.donorUserId = donorUserId;
    }

    public int getCryptocurrencyTransactionId() {
        return cryptocurrencyTransactionId;
    }

    public void setCryptocurrencyTransactionId(int cryptocurrencyTransactionId) {
        this.cryptocurrencyTransactionId = cryptocurrencyTransactionId;
    }

    public String getToCryptoAddress() {
        return toCryptoAddress;
    }

    public void setToCryptoAddress(String toCryptoAddress) {
        this.toCryptoAddress = toCryptoAddress;
    }

    public String getFromCryptoAddress() {
        return fromCryptoAddress;
    }
    
    public void setFromCryptoAddress(String fromCryptoAddress) {
        this.fromCryptoAddress = fromCryptoAddress;
    }


}