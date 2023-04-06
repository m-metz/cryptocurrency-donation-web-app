package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Data {

    private String type;
    private String id;
    private Attributes attributes;

    public Data() {

    }

    public Data(String type, String id, Attributes attributes) {
        this.type = type;
        this.id = id;
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttribute(Attributes attributes) {
        this.attributes = attributes;
    }

}
