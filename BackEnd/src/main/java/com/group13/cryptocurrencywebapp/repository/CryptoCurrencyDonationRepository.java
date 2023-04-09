package com.group13.cryptocurrencywebapp.repository;

import org.springframework.stereotype.Repository;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CryptoCurrencyDonationRepository extends JpaRepository<CryptoCurrencyDonation, Integer> {

    Optional<CryptoCurrencyDonation> findByDonationId(int donation_id);

    List<CryptoCurrencyDonation> findAllByDonorUserId(String userid);

    List<CryptoCurrencyDonation> findByStatusContaining(String string);

}
