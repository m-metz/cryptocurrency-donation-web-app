package com.group13.cryptocurrencywebapp.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.logging.LogLevel;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
public class GeminiApi {

    static String API_ENDPOINT = "https://api.sandbox.gemini.com";

    @Value("${gemini.api.key}")
    String apiKey;

    @Bean
    WebClient geminiClient() {
        return WebClient.builder()
                .baseUrl(API_ENDPOINT)
                .defaultHeader("X-GEMINI-APIKEY", apiKey)
                .defaultHeader("Content-Type", "text/plain")
                .defaultHeader("Content-Length", "0")
                .defaultHeader("Cache-Control", "no-cache")
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG,
                                AdvancedByteBufFormat.TEXTUAL)))
                .build();
    }

}
