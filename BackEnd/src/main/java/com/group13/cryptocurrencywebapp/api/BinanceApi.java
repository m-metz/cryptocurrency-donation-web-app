package com.group13.cryptocurrencywebapp.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.logging.LogLevel;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

/**
 * <pre>
 * Class Name: BinanceApi
 * 
 * Date Created: March 13, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that configures model Bean definition for WebClient object to be used
 * to make https requests to Binance API endpoints.
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
public class BinanceApi {

    /**
     * API_ENDPOINT basic URL for Binance SandBox Enviroment
     */
    static String API_ENDPOINT = "https://testnet.binance.vision/api";

    /**
     * apiKey used in the http resquests to Binance.
     * Value is retrieved from Spring Boot Memory under binance.api.key key.
     * binance.api.key configured in the application.properties env file
     */
    @Value("${binance.api.key}")
    String apiKey;

    /**
     * Bean model that builds binanceClient WebClient object to be used for the
     * http requests to Binance including base URL, header X-MBX-APIKEY (required by
     * binance) and logging.
     * 
     * @return WebClient object that will be injected automatically by Spring Boot.
     */
    @Bean
    WebClient binanceClient() {
        return WebClient.builder()
                .baseUrl(API_ENDPOINT)
                .defaultHeader("X-MBX-APIKEY", apiKey)
                // .clientConnector(new ReactorClientHttpConnector(
                // HttpClient.create().wiretap("reactor.netty.http.client.HttpClient",
                // LogLevel.DEBUG,
                // AdvancedByteBufFormat.TEXTUAL)))
                .build();
    }

}
