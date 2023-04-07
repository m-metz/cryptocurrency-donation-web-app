package com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data;

import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.attributes.Destination;
import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.attributes.Donor;
import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.attributes.Funds;
import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.attributes.Metadata;
import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.attributes.State;

public class Attributes {
    
    private Destination destination;
    private Donor donor;
    private Funds funds;
    private State state;
    private Metadata metadata;

    public State getState() {
        return state;
    }

    public String retrieveStatus(){
        return state.getProcessingStatus();
    }

    public Donor getDonor() {
        return donor;
    }

    public Funds getFunds() {
        return funds;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Destination getDestination() {
        return destination;
    }

    public String retrieveReceiptId() {
        return funds.getReceiptId();
    }
}
