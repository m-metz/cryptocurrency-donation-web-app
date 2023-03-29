package com.group13.cryptocurrencywebapp.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.logging.LogLevel;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
public class EtherscanApi {

    static String API_ENDPOINT = "https://api-goerli.etherscan.io/api";

    @Bean
    WebClient etherscanClient() {
        return WebClient.builder()
                .baseUrl(API_ENDPOINT)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG,
                                AdvancedByteBufFormat.TEXTUAL)))
                .build();
    }

}
