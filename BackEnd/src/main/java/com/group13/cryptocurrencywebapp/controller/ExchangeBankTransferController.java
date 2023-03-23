package com.group13.cryptocurrencywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.ExchangeBankTransfer;
import com.group13.cryptocurrencywebapp.service.ExchangeBankTransferService;

@RestController
@RequestMapping(path = "api/v1/exchangebanktransfer")
public class ExchangeBankTransferController {
    private final ExchangeBankTransferService exchangeBankTransferService;

    @Autowired
    public ExchangeBankTransferController(ExchangeBankTransferService exchangeBankTransferService) {
        this.exchangeBankTransferService = exchangeBankTransferService;
    }

    @GetMapping
    public List<ExchangeBankTransfer> getAllExchangeBankTransfers() {
        return exchangeBankTransferService.getAllExchangeBankTransfers();
    }

}
