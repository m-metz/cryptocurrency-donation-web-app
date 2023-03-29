package com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction;

public class Result {

    String timeStamp;
    String hash;
    String from;
    String gasUsed;
    String value;

    public Result() {

    }

    public Result(String timeStamp, String hash, String from, String gasUsed, String value) {
        this.timeStamp = timeStamp;
        this.hash = hash;
        this.from = from;
        this.gasUsed = gasUsed;
        this.value = value;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
