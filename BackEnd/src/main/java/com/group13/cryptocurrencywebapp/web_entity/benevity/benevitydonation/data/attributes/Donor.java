package com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.attributes;

import com.group13.cryptocurrencywebapp.web_entity.benevity.benevitydonation.data.attributes.donor.Address;

public class Donor {
    private Address address;
    private String email;
    private String fullName;
    private boolean receipted;

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean getReceipted() {
        return receipted;
    }   
    
}
