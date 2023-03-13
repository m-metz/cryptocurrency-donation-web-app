package com.group13.cryptocurrencywebapp.taxreceipt;

import com.group13.cryptocurrencywebapp.cryptocurrencydonation.CryptoCurrencyDonation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_receipt")
public class TaxReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_receipt_id")

    private int taxReceiptId;
    private float amount;
    private String givenNames;
    private String lastName;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String stateProvinceRegion;
    private String zipPostalCode;

    // @OneToOne(mappedBy = "tax_receipt")
    // private CryptoCurrencyDonation cryptoCurrencyDonation;

    public TaxReceipt(int taxReceiptId, float amount, String givenNames, String lastName,
            String email, String address1, String address2, String city, String country,
            String stateProvinceRegion, String zipPostalCode) {
        this.taxReceiptId = taxReceiptId;
        this.amount = amount;
        this.givenNames = givenNames;
        this.lastName = lastName;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.country = country;
        this.stateProvinceRegion = stateProvinceRegion;
        this.zipPostalCode = zipPostalCode;
    }

    public TaxReceipt(float amount, String givenNames, String lastName, String email,
            String address1, String address2, String city, String country,
            String stateProvinceRegion, String zipPostalCode) {
        this.amount = amount;
        this.givenNames = givenNames;
        this.lastName = lastName;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.country = country;
        this.stateProvinceRegion = stateProvinceRegion;
        this.zipPostalCode = zipPostalCode;
    }

    public int getTaxReceiptId() {
        return taxReceiptId;
    }

    public void setTaxReceiptId(int taxReceiptId) {
        this.taxReceiptId = taxReceiptId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateProvinceRegion() {
        return stateProvinceRegion;
    }

    public void setStateProvinceRegion(String stateProvinceRegion) {
        this.stateProvinceRegion = stateProvinceRegion;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

}
