package com.group13.cryptocurrencywebapp.web_entity.etherscan;

public class EthAddress {
    String status;
    String message;
    String result;

    public EthAddress() {

    }

    public EthAddress(String status, String message, String result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
