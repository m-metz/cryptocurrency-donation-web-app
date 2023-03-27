package com.group13.cryptocurrencywebapp.web_entity.exchange.gemini;

public class InstantOrder {

    private String orderId;
    private String pair;
    private String price;
    private String priceCurrency;
    private String side;
    private String quantity;
    private String totalSpend;
    private String totalSpendCurrency;
    private String fee;
    private String feeCurrency;
    private String depositFee;
    private String depositFeeCurrency;

    public InstantOrder() {

    }

    public InstantOrder(String orderId, String pair, String price, String priceCurrency, String side, String quantity,
            String totalSpend, String totalSpendCurrency, String fee, String feeCurrency, String depositFee,
            String depositFeeCurrency) {
        this.orderId = orderId;
        this.pair = pair;
        this.price = price;
        this.priceCurrency = priceCurrency;
        this.side = side;
        this.quantity = quantity;
        this.totalSpend = totalSpend;
        this.totalSpendCurrency = totalSpendCurrency;
        this.fee = fee;
        this.feeCurrency = feeCurrency;
        this.depositFee = depositFee;
        this.depositFeeCurrency = depositFeeCurrency;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(String totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getTotalSpendCurrency() {
        return totalSpendCurrency;
    }

    public void setTotalSpendCurrency(String totalSpendCurrency) {
        this.totalSpendCurrency = totalSpendCurrency;
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

    public String getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(String depositFee) {
        this.depositFee = depositFee;
    }

    public String getDepositFeeCurrency() {
        return depositFeeCurrency;
    }

    public void setDepositFeeCurrency(String depositFeeCurrency) {
        this.depositFeeCurrency = depositFeeCurrency;
    }

}
