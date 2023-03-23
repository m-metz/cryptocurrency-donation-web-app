package com.group13.cryptocurrencywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;
import com.group13.cryptocurrencywebapp.service.CryptoTransferService;

@RestController
@RequestMapping(path = "api/v1/cryptotransfer")
public class CryptoTransferController {
    private final CryptoTransferService cryptoTransferService;

    @Autowired
    public CryptoTransferController(CryptoTransferService cryptoTransferService) {
        this.cryptoTransferService = cryptoTransferService;
    }

    @GetMapping
    public List<CryptoTransfer> getAllCryptoTransfers() {
        return cryptoTransferService.getAllCryptoTransfers();
    }

}
