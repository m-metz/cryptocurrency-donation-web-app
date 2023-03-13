package com.group13.cryptocurrencywebapp.transaction;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController {
    public final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(){

        return transactionService.getAllTransactions();
    }

    @PostMapping
    public void registerNewTransaction(@RequestBody Transaction transaction){
        transactionService.addNewTransaction(transaction);
    }
    
}