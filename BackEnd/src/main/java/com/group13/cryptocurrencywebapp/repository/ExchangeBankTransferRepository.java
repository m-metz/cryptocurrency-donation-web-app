package com.group13.cryptocurrencywebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group13.cryptocurrencywebapp.entity.ExchangeBankTransfer;

@Repository
public interface ExchangeBankTransferRepository extends JpaRepository<ExchangeBankTransfer, Integer> {

}
