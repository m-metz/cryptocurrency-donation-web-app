package com.group13.cryptocurrencywebapp.web_entity.exchange.gemini;

public class Quote {

    private int quoteId;
    private String maxAgeMs;
    private String price;
    private String fee;
    private String feeCurrency;
    private String quantity;

    public Quote() {

    }

    public Quote(int quoteId, String maxAgeMs, String price, String fee, String feeCurrency, String quantity) {
        this.quoteId = quoteId;
        this.maxAgeMs = maxAgeMs;
        this.price = price;
        this.fee = fee;
        this.feeCurrency = feeCurrency;
        this.quantity = quantity;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteID(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getMaxAgeMs() {
        return maxAgeMs;
    }

    public void setMaxAgeMs(String maxAgeMs) {
        this.maxAgeMs = maxAgeMs;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setTotalSpend(String quantity) {
        this.quantity = quantity;
    }

}
