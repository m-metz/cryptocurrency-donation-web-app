package com.group13.cryptocurrencywebapp.cryptotransfer;

import java.util.List;

import org.springframework.stereotype.Service;
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
