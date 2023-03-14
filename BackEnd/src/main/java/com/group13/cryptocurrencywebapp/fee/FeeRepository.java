package com.group13.cryptocurrencywebapp.fee;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {

}
