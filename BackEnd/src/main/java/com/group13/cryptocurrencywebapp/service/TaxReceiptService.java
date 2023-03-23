package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group13.cryptocurrencywebapp.entity.TaxReceipt;
import com.group13.cryptocurrencywebapp.repository.TaxReceiptRepository;

@Service
public class TaxReceiptService {
    private final TaxReceiptRepository taxReceiptRepository;

    @Autowired
    public TaxReceiptService(TaxReceiptRepository taxReceiptRepository) {
        this.taxReceiptRepository = taxReceiptRepository;
    }

    public List<TaxReceipt> getAllTaxReceipts() {
        return taxReceiptRepository.findAll();
    }

}
