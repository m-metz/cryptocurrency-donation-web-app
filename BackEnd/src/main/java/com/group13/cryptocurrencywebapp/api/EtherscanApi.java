package com.group13.cryptocurrencywebapp.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.logging.LogLevel;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

/**
 * <pre>
 * Class Name: EtherscanApi
 * 
 * Date Created: March 15, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that configures model Bean definition for WebClient object to be used
 * to make https requests to Etherscan API endpoints.
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
public class EtherscanApi {

    /**
     * API_ENDPOINT basic URL for Etherscan Goerli Network
     */
    static String API_ENDPOINT = "https://api-goerli.etherscan.io/api";

    /**
     * Bean model that builds etherscanClient WebClient object to be used for the
     * http requests to Etherscan including base URL and logging.
     * 
     * @return WebClient object that will be injected automatically by Spring Boot.
     */
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
