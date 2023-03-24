package com.group13.cryptocurrencywebapp.controller;

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
import com.group13.cryptocurrencywebapp.web_entity.benevity.BenevityDonation;

@RestController
@RequestMapping(path = "api/v1/cryptocurrencydonation")
public class CryptoCurrencyDonationController {
    private final CryptoCurrencyDonationService cryptoCurrencyDonationService;
    private final BenevityService benevityService;

    @Autowired
    public CryptoCurrencyDonationController(CryptoCurrencyDonationService cryptoCurrencyDonationService,
                                            BenevityService benevityService) {
        this.cryptoCurrencyDonationService = cryptoCurrencyDonationService;
        this.benevityService = benevityService;
    }

    @GetMapping()
    public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
        return cryptoCurrencyDonationService.getAllCryptoDonations();
    }

    @GetMapping("/Benevity/id={id}")
    public String getBenevityDonation(@PathVariable String id){
        return benevityService.getDonationStatus(id);
    }

    @PostMapping("/Benevity/createDonation")
    public BenevityDonation createBenevityDonation(@RequestBody String benevityDonationStr){
        
        return benevityService.createDonation(benevityDonationStr);
    }
    

}
