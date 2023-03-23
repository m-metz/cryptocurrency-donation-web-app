package com.group13.cryptocurrencywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExchangeService {

    @Autowired
    @Qualifier("binanceClient")
    private WebClient webclient = WebClient.create();

    public String getAccountInfo(String id) {
        ResponseEntity<BenevityDonation> response = webclient.get()
                .uri("/donations/" + id)
                .retrieve()
                .toEntity(BenevityDonation.class)
                .block();

        BenevityDonation donation = response.getBody();
        String state = BenevityDonation.getState();

        return state;



}
