package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;
import com.group13.cryptocurrencywebapp.repository.CryptoTransferRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CryptoTransferService {
    private final CryptoTransferRepository cryptoTransferRepository;

    @Autowired
    public CryptoTransferService(CryptoTransferRepository cryptoTransferRepository) {
        this.cryptoTransferRepository = cryptoTransferRepository;
    }

    public List<CryptoTransfer> getAllCryptoTransfers() {
        return cryptoTransferRepository.findAll();
    }

}
