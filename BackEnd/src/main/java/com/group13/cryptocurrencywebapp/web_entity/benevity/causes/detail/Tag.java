package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Tag {

    private String type;
    private String value;

    public Tag() {

    }

    public Tag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
