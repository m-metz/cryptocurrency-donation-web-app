package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group13.cryptocurrencywebapp.entity.Fee;
import com.group13.cryptocurrencywebapp.repository.FeeRepository;

@Service
public class FeeService {
    private final FeeRepository feeRepository;

    @Autowired
    public FeeService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

}
