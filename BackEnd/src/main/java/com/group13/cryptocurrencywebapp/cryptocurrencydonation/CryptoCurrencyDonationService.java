package com.group13.cryptocurrencywebapp.cryptocurrencydonation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyDonationService {
    private final CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository;

    @Autowired
    public CryptoCurrencyDonationService(CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository) {
        this.cryptoCurrencyDonationRepository = cryptoCurrencyDonationRepository;
    }

    public List<CryptoCurrencyDonation> getAllCryptoDonations() {
        return cryptoCurrencyDonationRepository.findAll();
    }

}
