package com.group13.cryptocurrencywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.Fee;
import com.group13.cryptocurrencywebapp.service.FeeService;

@RestController
@RequestMapping(path = "api/v1/fee")
public class FeeController {
    private final FeeService feeService;

    @Autowired
    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping
    public List<Fee> getAllFees() {
        return feeService.getAllFees();
    }

}
