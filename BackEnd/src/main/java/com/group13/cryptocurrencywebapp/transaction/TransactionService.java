package com.group13.cryptocurrencywebapp.transaction;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void addNewTransaction(Transaction transaction) {
        Optional<Transaction> transactionById = transactionRepository
                .findTransactionByTransactionId(transaction.getTransactionId());
        if (transactionById.isPresent()) {
            throw new IllegalStateException();
        }
        transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(int transactionId) {
        Optional<Transaction> transactionById = transactionRepository.findTransactionByTransactionId(transactionId);
        if (!transactionById.isPresent()) {
            throw new IllegalStateException();
        }
        return transactionById.get();
    }

}