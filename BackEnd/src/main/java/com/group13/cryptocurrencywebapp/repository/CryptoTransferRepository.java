package com.group13.cryptocurrencywebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;

public interface CryptoTransferRepository extends JpaRepository<CryptoTransfer, Integer> {

}
