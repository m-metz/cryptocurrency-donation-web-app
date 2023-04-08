package com.group13.cryptocurrencywebapp.web_entity.benevity;

import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.Data;

public class BenevityDonation {
    private Data data;

    public Data getData() {
        return data;
    }

    public String retrieveStatus(){
        return data.retrieveStatus();
    }

    public String retrieveDonationId(){
        return data.getId();
    }

    public String retrieveReceiptId(){
        return data.retrieveReceiptId();
    }
    
}
