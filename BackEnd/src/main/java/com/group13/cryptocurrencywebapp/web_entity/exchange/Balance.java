package com.group13.cryptocurrencywebapp.web_entity.exchange;

public class Balance {

    private String asset;
    private String free;
    private String locked;

    public Balance() {

    }

    public Balance(String asset, String free, String locked) {
        this.asset = asset;
        this.free = free;
        this.locked = locked;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

}
