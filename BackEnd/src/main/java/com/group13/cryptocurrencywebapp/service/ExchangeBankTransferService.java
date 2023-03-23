package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group13.cryptocurrencywebapp.entity.ExchangeBankTransfer;
import com.group13.cryptocurrencywebapp.repository.ExchangeBankTransferRepository;

@Service
public class ExchangeBankTransferService {
    private final ExchangeBankTransferRepository exchangeBankTransferRepository;

    @Autowired
    public ExchangeBankTransferService(ExchangeBankTransferRepository exchangeBankTransferRepository) {
        this.exchangeBankTransferRepository = exchangeBankTransferRepository;
    }

    public List<ExchangeBankTransfer> getAllExchangeBankTransfers() {
        return exchangeBankTransferRepository.findAll();
    }

}
