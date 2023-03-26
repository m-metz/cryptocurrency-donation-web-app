package com.group13.cryptocurrencywebapp.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.service.BenevityService;
import com.group13.cryptocurrencywebapp.service.CryptoCurrencyDonationService;
import com.group13.cryptocurrencywebapp.service.ExchangeService;
import com.group13.cryptocurrencywebapp.service.GeminiService;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeAccount;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.Balance;
import com.group13.cryptocurrencywebapp.web_entity.benevity.BenevityDonation;

@RestController
@RequestMapping(path = "api/v1/cryptocurrencydonation")
public class CryptoCurrencyDonationController {
    private final CryptoCurrencyDonationService cryptoCurrencyDonationService;
    private final BenevityService benevityService;
    private final ExchangeService exchangeService;
    private final GeminiService geminiService;

    @Autowired
    public CryptoCurrencyDonationController(CryptoCurrencyDonationService cryptoCurrencyDonationService,
            BenevityService benevityService, ExchangeService exchangeService, GeminiService geminiService) {
        this.cryptoCurrencyDonationService = cryptoCurrencyDonationService;
        this.benevityService = benevityService;
        this.exchangeService = exchangeService;
        this.geminiService = geminiService;
    }

    // @GetMapping()
    // public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
    // return cryptoCurrencyDonationService.getAllCryptoDonations();
    // }

    @GetMapping(path = "exchange/account")
    public ExchangeAccount getAccountInfo() throws Exception {
        return exchangeService.getAccountInfo();
    }

    @GetMapping(path = "gemini/balances")
    public List<Balance> getGeminiBalancesInfo() throws UnsupportedEncodingException {
        return geminiService.getBalancesInfo();
    }

    @GetMapping("/Benevity/id={id}")
    public String getBenevityDonation(@PathVariable String id) {
        return benevityService.getDonationStatus(id);
    }

    @PostMapping("/Benevity/createDonation")
    public BenevityDonation createBenevityDonation(@RequestBody String benevityDonationStr) {

        return benevityService.createDonation(benevityDonationStr);
    }

    @PostMapping("/createDonation")
    public CryptoCurrencyDonation addNewDonation(@RequestBody CryptoCurrencyDonation cryptoDonation) {
        return cryptoCurrencyDonationService.createNewDonation(cryptoDonation);

    }

    @GetMapping("/getDonation/all")
    public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
        return cryptoCurrencyDonationService.getAllDonations();
    }

    @PostMapping(path = "exchange/newtrade/amount={amount}")
    public String executeNewTrade(@PathVariable int amount) throws Exception {
        return exchangeService.executeNewTrade(amount);
    }

}
