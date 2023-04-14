package com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.txcheck;

import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.Result;

public class CheckResult {

    String status;
    String message;
    Result result;

    public CheckResult() {

    }

    public CheckResult(String status, String message, Result result) {
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
