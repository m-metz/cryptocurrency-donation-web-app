package com.group13.cryptocurrencywebapp.exchangebanktransfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeBankTransferRepository extends JpaRepository<ExchangeBankTransfer, Integer> {

}
