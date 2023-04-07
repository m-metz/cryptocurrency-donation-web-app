package com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation;

import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.Attributes;

public class Data {
    private String type;
    private String id;
    private Attributes attributes;

    public String getId() {
        return id;
    }
    public Attributes getAttributes() {
        return attributes;
    }

    public String retrieveStatus(){
        return attributes.retrieveStatus();
    }
    
    public String getType() {
        return type;
    }
    public String retrieveReceiptId() {
        return attributes.retrieveReceiptId();
    }
    
}
