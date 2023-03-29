package com.group13.cryptocurrencywebapp.web_entity.etherscan;

public class Result {

    String ethusd;
    String ethusd_timestamp;

    public Result() {

    }

    public Result(String ethusd, String ethusd_timestamp) {
        this.ethusd = ethusd;
        this.ethusd_timestamp = ethusd_timestamp;
    }

    public String getEthusd() {
        return ethusd;
    }

    public void setEthusd(String ethusd) {
        this.ethusd = ethusd;
    }

    public String getEthusd_timestamp() {
        return ethusd_timestamp;
    }

    public void setEthusd_timestamp(String ethusd_timestamp) {
        this.ethusd_timestamp = ethusd_timestamp;
    }

}
