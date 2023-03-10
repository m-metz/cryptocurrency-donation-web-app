package com.group13.cryptocurrencywebapp.taxreceipt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    

}
