package com.group13.cryptocurrencywebapp.web_entity.exchange.gemini;

public class Balance {

    private String type;
    private String currency;
    private String amount;
    private String available;
    private String availableForWithdrawal;

    public Balance() {

    }

    public Balance(String type, String currency, String amount, String available, String availableForWithdrawal) {
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.available = available;
        this.availableForWithdrawal = availableForWithdrawal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAvailableForWithdrawal() {
        return availableForWithdrawal;
    }

    public void setAvailableForWithdrawal(String availableForWithdrawal) {
        this.availableForWithdrawal = availableForWithdrawal;
    }

}
