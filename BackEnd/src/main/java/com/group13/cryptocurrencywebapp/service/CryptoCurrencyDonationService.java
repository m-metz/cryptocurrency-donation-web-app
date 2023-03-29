package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.repository.CryptoCurrencyDonationRepository;

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

    public CryptoCurrencyDonation createNewDonation(CryptoCurrencyDonation cryptoDonation) {

        cryptoDonation = cryptoCurrencyDonationRepository.save(cryptoDonation);
        return cryptoDonation;
    }

    public List<CryptoCurrencyDonation> getAllDonations() {
        return cryptoCurrencyDonationRepository.findAll();
    }

}
