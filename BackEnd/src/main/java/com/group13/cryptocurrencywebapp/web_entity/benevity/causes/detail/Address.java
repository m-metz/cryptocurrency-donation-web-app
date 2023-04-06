package com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail;

public class Address {

    private String street;
    private String line2;
    private String city;
    private String postcode;
    private Region region;
    private Country country;

    public Address() {

    }

    public Address(String street, String line2, String city, String postcode, Region region, Country country) {
        this.street = street;
        this.line2 = line2;
        this.city = city;
        this.postcode = postcode;
        this.region = region;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
