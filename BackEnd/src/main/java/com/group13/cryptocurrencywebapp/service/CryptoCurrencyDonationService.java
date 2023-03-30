package com.group13.cryptocurrencywebapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.entity.Trade;
import com.group13.cryptocurrencywebapp.repository.CryptoCurrencyDonationRepository;
import com.group13.cryptocurrencywebapp.repository.FeeRepository;
import com.group13.cryptocurrencywebapp.repository.TradeRepository;

@Service
public class CryptoCurrencyDonationService {
    private final CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository;
    private final TradeService tradeService;
    private final TradeRepository tradeRepository;
    private final FeeRepository feeRepository;

    @Autowired
    public CryptoCurrencyDonationService(CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository,
            TradeService tradeService, TradeRepository tradeRepository, FeeRepository feeRepository) {
        this.cryptoCurrencyDonationRepository = cryptoCurrencyDonationRepository;
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
        this.feeRepository = feeRepository;
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

    public void createTrade(int donationId, float amount) {
        CryptoCurrencyDonation donation = cryptoCurrencyDonationRepository.findById(donationId).get();
        Trade newTrade = new Trade();

        newTrade.setCurrency("ETH");
        newTrade.setAmount(amount);
        newTrade.setTime(LocalDateTime.now());
        newTrade.setToCurrency("USD");

    }

}
