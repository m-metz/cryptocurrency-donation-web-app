package com.group13.cryptocurrencywebapp.transaction;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    public Optional<Transaction> findTransactionById(int transactionId);
    
}
