package com.group13.cryptocurrencywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.service.CryptoCurrencyDonationService;
import com.group13.cryptocurrencywebapp.service.ExchangeService;
import com.group13.cryptocurrencywebapp.web_entity.exchange.ExchangeAccount;

@RestController
@RequestMapping(path = "api/v1/cryptocurrencydonation")
public class CryptoCurrencyDonationController {
    private final CryptoCurrencyDonationService cryptoCurrencyDonationService;
    private final ExchangeService exchangeService;

    @Autowired
    public CryptoCurrencyDonationController(CryptoCurrencyDonationService cryptoCurrencyDonationService,
            ExchangeService exchangeService) {
        this.cryptoCurrencyDonationService = cryptoCurrencyDonationService;
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
        return cryptoCurrencyDonationService.getAllCryptoDonations();
    }

    @GetMapping(path = "exchange/account")
    public ExchangeAccount getAccountInfo() throws Exception {
        return exchangeService.getAccountInfo();
    }

}
