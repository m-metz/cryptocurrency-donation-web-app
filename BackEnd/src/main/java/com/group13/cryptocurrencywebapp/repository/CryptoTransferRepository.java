package com.group13.cryptocurrencywebapp.repository;

//import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;

public interface CryptoTransferRepository extends JpaRepository<CryptoTransfer, Integer> {

    //Optional<CryptoTransfer> findByDonationId(int donation_id);
}
