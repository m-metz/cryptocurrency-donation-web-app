package com.group13.cryptocurrencywebapp.cryptocurrencydonation;

import com.group13.cryptocurrencywebapp.cryptotransfer.CryptoTransfer;
import com.group13.cryptocurrencywebapp.exchangebanktransfer.ExchangeBankTransfer;
import com.group13.cryptocurrencywebapp.taxreceipt.TaxReceipt;
import com.group13.cryptocurrencywebapp.trade.Trade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "currency_donation")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_receipt_id")
    private TaxReceipt taxReceipt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_id")
    private Trade trade;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crypto_transfer_id")
    private CryptoTransfer cryptoTransfer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exchange_bank_transfer_id")
    private ExchangeBankTransfer exchangeBankTransfer;

    public CryptoCurrencyDonation(int donationId, int nonProfitId, int donorUserId,
            int cryptocurrencyTransactionId, String toCryptoAddress, String fromCryptoAddress) {
        this.donationId = donationId;
        this.nonProfitId = nonProfitId;
        this.donorUserId = donorUserId;
        this.cryptocurrencyTransactionId = cryptocurrencyTransactionId;
        this.toCryptoAddress = toCryptoAddress;
        this.fromCryptoAddress = fromCryptoAddress;
    }

    public CryptoCurrencyDonation(int nonProfitId, int donorUserId, int cryptocurrencyTransactionId,
            String toCryptoAddress, String fromCryptoAddress) {
        this.nonProfitId = nonProfitId;
        this.donorUserId = donorUserId;
        this.cryptocurrencyTransactionId = cryptocurrencyTransactionId;
        this.toCryptoAddress = toCryptoAddress;
        this.fromCryptoAddress = fromCryptoAddress;
    }

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