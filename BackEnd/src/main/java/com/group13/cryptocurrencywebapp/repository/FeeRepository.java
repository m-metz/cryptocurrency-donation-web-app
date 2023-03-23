package com.group13.cryptocurrencywebapp.repository;

import org.springframework.stereotype.Repository;

import com.group13.cryptocurrencywebapp.entity.Fee;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {

}
