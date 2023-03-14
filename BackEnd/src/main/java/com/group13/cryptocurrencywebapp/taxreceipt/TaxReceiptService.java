package com.group13.cryptocurrencywebapp.taxreceipt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
