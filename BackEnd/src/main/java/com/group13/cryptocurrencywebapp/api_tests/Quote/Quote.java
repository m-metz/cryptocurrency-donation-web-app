package com.group13.cryptocurrencywebapp.api_tests.Quote;

public class Quote {
    private String type;
    private Value value;

    public String getType() {
        return type;
    }
    public Value getValue() {
        return value;
    }

    public String toString(){
        return "type: "+ type + "\nvalue" + value.toString();
    }

    
}
