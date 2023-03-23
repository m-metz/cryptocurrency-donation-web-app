package com.group13.cryptocurrencywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.TaxReceipt;
import com.group13.cryptocurrencywebapp.service.TaxReceiptService;

@RestController
@RequestMapping(path = "api/v1/taxreceipt")
public class TaxReceiptController {
    private final TaxReceiptService taxReceiptService;

    @Autowired
    public TaxReceiptController(TaxReceiptService taxReceiptService) {
        this.taxReceiptService = taxReceiptService;
    }

    @GetMapping
    public List<TaxReceipt> getAllTaxReceipts() {
        return taxReceiptService.getAllTaxReceipts();
    }

}
