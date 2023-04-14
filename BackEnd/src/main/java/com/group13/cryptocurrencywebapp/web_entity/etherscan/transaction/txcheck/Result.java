package com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.txcheck;

public class Result {

    private Status status;

    public Result() {

    }

    public Result(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
