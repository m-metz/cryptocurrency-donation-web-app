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

}
