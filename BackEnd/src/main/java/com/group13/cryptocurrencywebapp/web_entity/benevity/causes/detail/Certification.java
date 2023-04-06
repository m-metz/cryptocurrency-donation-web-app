package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Certification {

    private String status;
    private String effective_timestamp;
    private String expiry_timestamp;

    public Certification() {

    }

    public Certification(String status, String effective_timestamp, String expiry_timestamp) {
        this.status = status;
        this.effective_timestamp = effective_timestamp;
        this.expiry_timestamp = expiry_timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEffective_timestamp() {
        return effective_timestamp;
    }

    public void setEffective_timestamp(String effective_timestamp) {
        this.effective_timestamp = effective_timestamp;
    }

    public String getExpiry_timestamp() {
        return expiry_timestamp;
    }

    public void setExpiry_timestamp(String expiry_timestamp) {
        this.expiry_timestamp = expiry_timestamp;
    }

}
