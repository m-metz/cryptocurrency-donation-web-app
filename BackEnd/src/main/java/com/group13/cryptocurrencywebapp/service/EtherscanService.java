package com.group13.cryptocurrencywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.web_entity.etherscan.EthAddress;

@Service
public class EtherscanService {

    @Autowired
    @Qualifier("etherscanClient")
    private WebClient webclient = WebClient.create();

    @Value("${etherscan.api.key}")
    String apiKey;

    public String validateAddress(String address) {
        String uri = "?module=account&action=balance&address=" + address + "&tag=latest&apikey=" + apiKey;

        ResponseEntity<EthAddress> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(EthAddress.class)
                .block();

        if (checkMessage(response.getBody()) == true) {
            return "Valid Ethereum Address";
        } else {
            return "Invalid Address";
        }

    }

    public boolean checkMessage(EthAddress address) {
        return address.getMessage().equals("OK");
    }

}
