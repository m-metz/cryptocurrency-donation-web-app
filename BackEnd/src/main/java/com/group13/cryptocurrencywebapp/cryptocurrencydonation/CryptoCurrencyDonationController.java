package com.group13.cryptocurrencywebapp.cryptocurrencydonation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
