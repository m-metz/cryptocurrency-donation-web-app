package com.group13.cryptocurrencywebapp.web_entity.benevity.causes;

public class Data {
    private String id;
    private Attributes attributes;

    public Data() {

    }

    public Data(String id, Attributes attributes) {
        this.id = id;
        this.attributes = attributes;
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

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

}
