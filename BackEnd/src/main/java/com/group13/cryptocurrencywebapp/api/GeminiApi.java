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
 * Class Name: GeminiApi
 * 
 * Date Created: March 15, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that configures model Bean definition for WebClient object to be used
 * to make https requests to Gemini API endpoints.
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
public class GeminiApi {

    /**
     * API_ENDPOINT basic URL for Gemini SandBox Enviroment
     */
    static String API_ENDPOINT = "https://api.sandbox.gemini.com";

    /**
     * apiKey used in the http resquests to Gemini.
     * Value is retrieved from Spring Boot Memory under gemini.api.key key.
     * gemini.api.key configured in the application.properties env file
     */
    @Value("${gemini.api.key}")
    String apiKey;

    /**
     * Bean model that builds geminiClient WebClient object to be used for the
     * http requests to Gemini including base URL, default headers requested by
     * Gemini X-GEMINI-APIKEY, Content-Type, Content-Length,Cache-Control and
     * logging.
     * 
     * @return WebClient object that will be injected automatically by Spring Boot.
     */
    @Bean
    WebClient geminiClient() {
        return WebClient.builder()
                .baseUrl(API_ENDPOINT)
                .defaultHeader("X-GEMINI-APIKEY", apiKey)
                .defaultHeader("Content-Type", "text/plain")
                .defaultHeader("Content-Length", "0")
                .defaultHeader("Cache-Control", "no-cache")
                // .clientConnector(new ReactorClientHttpConnector(
                // HttpClient.create().wiretap("reactor.netty.http.client.HttpClient",
                // LogLevel.DEBUG,
                // AdvancedByteBufFormat.TEXTUAL)))
                .build();
    }

}
