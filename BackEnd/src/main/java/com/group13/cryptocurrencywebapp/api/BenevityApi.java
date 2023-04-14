package com.group13.cryptocurrencywebapp.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.logging.LogLevel;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

/**
 * <pre>
 * Class Name: BenevityApi
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that configures model Bean definition for WebClient object to be used
 * to make https requests to Benevity API endpoints.
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
public class BenevityApi {

        /**
         * API_ENDPOINT basic URL for Benevity Staging API endpoints
         */
        static String API_ENDPOINT = "https://api.benevity-staging.org";

        /**
         * Bean model that builds benevityClient WebClient object to be used for the
         * http requests to Benevity including base URL, ouath2 configuration and
         * logging.
         * 
         * @return WebClient object that will be injected automatically by Spring Boot.
         */
        @Bean
        WebClient benevityClient(OAuth2AuthorizedClientManager clientManager) {

                ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = //
                                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientManager);
                oauth2.setDefaultClientRegistrationId("benevity");
                return WebClient.builder() //
                                .baseUrl(API_ENDPOINT) //
                                .apply(oauth2.oauth2Configuration()) //
                                // .clientConnector(new ReactorClientHttpConnector(
                                // HttpClient.create().wiretap("reactor.netty.http.client.HttpClient",
                                // LogLevel.DEBUG,
                                // AdvancedByteBufFormat.TEXTUAL)))
                                .build();
        }

}
