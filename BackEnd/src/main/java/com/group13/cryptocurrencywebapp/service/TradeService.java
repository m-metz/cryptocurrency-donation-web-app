package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group13.cryptocurrencywebapp.entity.Trade;
import com.group13.cryptocurrencywebapp.repository.TradeRepository;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

}
