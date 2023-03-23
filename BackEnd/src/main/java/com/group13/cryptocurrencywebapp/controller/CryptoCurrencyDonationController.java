package com.group13.cryptocurrencywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.service.CryptoCurrencyDonationService;

@RestController
@RequestMapping(path = "api/v1/cryptocurrencydonation")
public class CryptoCurrencyDonationController {
    private final CryptoCurrencyDonationService cryptoCurrencyDonationService;

    @Autowired
    public CryptoCurrencyDonationController(CryptoCurrencyDonationService cryptoCurrencyDonationService) {
        this.cryptoCurrencyDonationService = cryptoCurrencyDonationService;
    }

    @GetMapping
    public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
        return cryptoCurrencyDonationService.getAllCryptoDonations();
    }

}
