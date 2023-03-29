package com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction;

import java.util.List;

public class EthTransactionList {

    String status;
    String message;
    List<Result> result;

    public EthTransactionList() {

    }

    public EthTransactionList(String status, String message, List<Result> result) {
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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}
