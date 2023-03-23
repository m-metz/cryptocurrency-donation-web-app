package com.group13.cryptocurrencywebapp.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BinanceApi {

    static String API_ENDPOINT = "https://testnet.binance.vision/api";

    @Value("${binance.api.key}")
    String apiKey;

    @Bean
    WebClient binanceClient() {
        return WebClient.builder()
                .baseUrl(API_ENDPOINT)
                .defaultHeader("X-MBX-APIKEY", apiKey)
                .build();
    }

}
