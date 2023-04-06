package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Logo {

    private String size;
    private String url;

    public Logo() {

    }

    public Logo(String size, String url) {
        this.size = size;
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
