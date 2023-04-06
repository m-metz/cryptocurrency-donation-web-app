package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Region {

    private String name;
    private String alpha_code;

    public Region() {

    }

    public Region(String name, String alpha_code) {
        this.name = name;
        this.alpha_code = alpha_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha_code() {
        return alpha_code;
    }

    public void setAlpha_code(String alpha_code) {
        this.alpha_code = alpha_code;
    }

}
