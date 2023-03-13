package com.group13.cryptocurrencywebapp.cryptocurrencydonation;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CryptoCurrencyDonationRepository extends JpaRepository<CryptoCurrencyDonation, Integer> {

}
