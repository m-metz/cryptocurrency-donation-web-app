package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;
import com.group13.cryptocurrencywebapp.repository.CryptoCurrencyDonationRepository;
import com.group13.cryptocurrencywebapp.repository.CryptoTransferRepository;

@Service
public class CryptoCurrencyDonationService {
    private final CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository;
    private final CryptoTransferRepository cryptoTransferRepository;

    @Autowired
    public CryptoCurrencyDonationService(CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository,
                                        CryptoTransferRepository cryptoTransferRepository) {
        this.cryptoCurrencyDonationRepository = cryptoCurrencyDonationRepository;
        this.cryptoTransferRepository = cryptoTransferRepository;
    }

    public CryptoCurrencyDonation createNewDonation(CryptoCurrencyDonation cryptoDonation) {

        cryptoDonation = cryptoCurrencyDonationRepository.save(cryptoDonation);
        return cryptoDonation;
    }

    public List<CryptoCurrencyDonation> getAllDonations() {
        return cryptoCurrencyDonationRepository.findAll();
    }

    public List<CryptoTransfer> getAllDeposits() {
        return cryptoTransferRepository.findAll();
    }

    public CryptoTransfer createDeposit(CryptoTransfer cryptoTransfer) {

        cryptoTransfer = cryptoTransferRepository.save(cryptoTransfer);
        return null;
    }


}
