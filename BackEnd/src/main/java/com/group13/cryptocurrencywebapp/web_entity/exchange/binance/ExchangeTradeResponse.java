package com.group13.cryptocurrencywebapp.web_entity.exchange.binance;

import java.util.List;

public class ExchangeTradeResponse {

    private String symbol;
    private String clientOrderId;
    private long transactTime;
    private String price;
    private String status;
    private String side;
    private List<Fill> fills;

    public ExchangeTradeResponse() {

    }

    public ExchangeTradeResponse(String symbol, String clientOrderId, long transactTime, String price, String status,
            String side, List<Fill> fills) {
        this.symbol = symbol;
        this.clientOrderId = clientOrderId;
        this.transactTime = transactTime;
        this.price = price;
        this.status = status;
        this.side = side;
        this.fills = fills;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public long getTransactTime() {
        return transactTime;
    }

    public void setTransactTime(long transactTime) {
        this.transactTime = transactTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public List<Fill> getFills() {
        return fills;
    }

    public void setFills(List<Fill> fills) {
        this.fills = fills;
    }

}
