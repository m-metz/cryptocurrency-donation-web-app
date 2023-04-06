package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class OwnerVerified {

    private boolean status;
    private String timestamp;

    public OwnerVerified() {

    }

    public OwnerVerified(boolean status, String timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
