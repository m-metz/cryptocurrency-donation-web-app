package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.repository.CryptoCurrencyDonationRepository;
import reactor.core.publisher.Mono;

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
