package com.group13.cryptocurrencywebapp.web_entity.exchange.gemini;

public class Price {

    private String pair;
    private String price;
    private String percentChange24h;

    public Price() {

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

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

}
