package com.group13.cryptocurrencywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.Trade;
import com.group13.cryptocurrencywebapp.service.TradeService;

@RestController
@RequestMapping(path = "api/v1/trade")
public class TradeController {
    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping
    public List<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }

}
