package com.group13.cryptocurrencywebapp.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

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
@ConfigurationProperties(prefix = "binance.api")
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
    String key = "";

    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }

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
                .defaultHeader("X-MBX-APIKEY", key)
                // .clientConnector(new ReactorClientHttpConnector(
                // HttpClient.create().wiretap("reactor.netty.http.client.HttpClient",
                // LogLevel.DEBUG,
                // AdvancedByteBufFormat.TEXTUAL)))
                .build();
    }

}
