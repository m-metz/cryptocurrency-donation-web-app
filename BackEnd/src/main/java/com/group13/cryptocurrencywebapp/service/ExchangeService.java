package com.group13.cryptocurrencywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.config.Signature;
import com.group13.cryptocurrencywebapp.web_entity.exchange.ExchangeAccount;

@Service
public class ExchangeService {

    private Signature sign = new Signature();

    @Value("${binance.api.secret}")
    String secret;

    @Autowired
    @Qualifier("binanceClient")
    private WebClient webclient = WebClient.create();

    public ExchangeAccount getAccountInfo() throws Exception {
        String apiEndpoint = "/v3/account?";
        String queryParams = "timestamp=" + System.currentTimeMillis();
        String signature = sign.getSignature(queryParams, secret);
        String payload = apiEndpoint + queryParams + "&signature=" + signature;

        ResponseEntity<ExchangeAccount> response = webclient.get()
                .uri(payload)
                .retrieve()
                .toEntity(ExchangeAccount.class)
                .block();

        return response.getBody();
    }

}
