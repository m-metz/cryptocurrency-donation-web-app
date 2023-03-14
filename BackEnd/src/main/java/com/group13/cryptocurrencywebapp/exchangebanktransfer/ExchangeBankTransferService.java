package com.group13.cryptocurrencywebapp.exchangebanktransfer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
