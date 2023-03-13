package com.group13.cryptocurrencywebapp.cryptotransfer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
