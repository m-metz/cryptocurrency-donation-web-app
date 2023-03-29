package com.group13.cryptocurrencywebapp.service;

import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.group13.cryptocurrencywebapp.web_entity.etherscan.EthAddress;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.Price;

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

        if (response.getBody() != null) {
            if (checkMessage(response.getBody().getMessage()) == true) {
                return "Valid Ethereum Address";
            } else {
                return "Invalid Address";
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
        }

    }

    public boolean checkMessage(String message) {

        return message.equals("OK");
    }

    public Price getEthPrice() {
        String uri = "?module=stats&action=ethprice&apikey=" + apiKey;

        ResponseEntity<Price> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(Price.class)
                .block();
        Price price = response.getBody();
        if (price != null) {
            if (checkMessage(price.getMessage()) == true) {
                return price;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
        }

    }

}
