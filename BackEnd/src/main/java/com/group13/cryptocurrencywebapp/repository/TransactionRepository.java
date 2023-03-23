package com.group13.cryptocurrencywebapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group13.cryptocurrencywebapp.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public Optional<Transaction> findTransactionByTransactionId(int transactionId);

}
