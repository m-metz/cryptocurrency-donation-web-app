package com.group13.cryptocurrencywebapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.Transaction;
import com.group13.cryptocurrencywebapp.service.TransactionService;

@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController {
    public final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions() {

        return transactionService.getAllTransactions();
    }

    @PostMapping
    public void registerNewTransaction(@RequestBody Transaction transaction) {
        transactionService.addNewTransaction(transaction);
    }

}