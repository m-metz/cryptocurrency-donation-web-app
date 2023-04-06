package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Country {

    private String numeric_code;
    private String alpha_code;
    private String name;

    public Country() {

    }

    public Country(String numeric_code, String alpha_code, String name) {
        this.numeric_code = numeric_code;
        this.alpha_code = alpha_code;
        this.name = name;
    }

    public String getNumeric_code() {
        return numeric_code;
    }

    public void setNumeric_code(String numeric_code) {
        this.numeric_code = numeric_code;
    }

    public String getAlpha_code() {
        return alpha_code;
    }

    public void setAlpha_code(String alpha_code) {
        this.alpha_code = alpha_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
