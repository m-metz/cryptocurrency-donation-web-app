package com.group13.cryptocurrencywebapp.entity;

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

    private String nonProfitId;
    private String donorUserId;
    private String cryptocurrencyTxId;

    private String toCryptoAddress;
    private String fromCryptoAddress;
    private String status;

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

    public CryptoCurrencyDonation() {
    }

    public CryptoCurrencyDonation(int donationId, String nonProfitId, String donorUserId,
            String cryptocurrencyTxId, String toCryptoAddress, String fromCryptoAddress, String status,
            TaxReceipt taxReceipt, Trade trade, CryptoTransfer cryptoTransfer,
            ExchangeBankTransfer exchangeBankTransfer) {
        this.donationId = donationId;
        this.nonProfitId = nonProfitId;
        this.donorUserId = donorUserId;
        this.cryptocurrencyTxId = cryptocurrencyTxId;
        this.toCryptoAddress = toCryptoAddress;
        this.fromCryptoAddress = fromCryptoAddress;
        this.status = status;
        this.taxReceipt = taxReceipt;
        this.trade = trade;
        this.cryptoTransfer = cryptoTransfer;
        this.exchangeBankTransfer = exchangeBankTransfer;
    }

    public CryptoCurrencyDonation(String nonProfitId, String donorUserId, String cryptocurrencyTxId,
            String toCryptoAddress, String fromCryptoAddress, String status, TaxReceipt taxReceipt, Trade trade,
            CryptoTransfer cryptoTransfer, ExchangeBankTransfer exchangeBankTransfer) {
        this.nonProfitId = nonProfitId;
        this.donorUserId = donorUserId;
        this.cryptocurrencyTxId = cryptocurrencyTxId;
        this.toCryptoAddress = toCryptoAddress;
        this.fromCryptoAddress = fromCryptoAddress;
        this.status = status;
        this.taxReceipt = taxReceipt;
        this.trade = trade;
        this.cryptoTransfer = cryptoTransfer;
        this.exchangeBankTransfer = exchangeBankTransfer;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getNonProfitId() {
        return nonProfitId;
    }

    public void setNonProfitId(String nonProfitId) {
        this.nonProfitId = nonProfitId;
    }

    public String getDonorUserId() {
        return donorUserId;
    }

    public void setDonorUserId(String donorUserId) {
        this.donorUserId = donorUserId;
    }

    public String getCryptocurrencyTxId() {
        return cryptocurrencyTxId;
    }

    public void setCryptocurrencyTransactionId(String cryptocurrencyTxId) {
        this.cryptocurrencyTxId = cryptocurrencyTxId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaxReceipt getTaxReceipt() {
        return taxReceipt;
    }

    public void setTaxReceipt(TaxReceipt taxReceipt) {
        this.taxReceipt = taxReceipt;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public CryptoTransfer getCryptoTransfer() {
        return cryptoTransfer;
    }

    public void setCryptoTransfer(CryptoTransfer cryptoTransfer) {
        this.cryptoTransfer = cryptoTransfer;
    }

    public ExchangeBankTransfer getExchangeBankTransfer() {
        return exchangeBankTransfer;
    }

    public void setExchangeBankTransfer(ExchangeBankTransfer exchangeBankTransfer) {
        this.exchangeBankTransfer = exchangeBankTransfer;
    }

}