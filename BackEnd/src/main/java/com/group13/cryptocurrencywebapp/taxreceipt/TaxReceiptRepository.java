package com.group13.cryptocurrencywebapp.taxreceipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxReceiptRepository extends JpaRepository<TaxReceipt, Integer> {

}
