package com.group13.cryptocurrencywebapp.api_tests.Quote;

public class Value {
    private int id;
    private String quote;

    public int getId() {
        return id;
    }
    
    public String getQuote() {
        return quote;
    }

    public String toString(){   
        return "id: "+ id +"\nquote: "+ quote;
    }

}
