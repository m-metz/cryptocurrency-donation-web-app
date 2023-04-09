package com.group13.cryptocurrencywebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

@Configuration
public class OAuth2ClientManagerConfig {

        @Bean
        public OAuth2AuthorizedClientManager clientManager( //
                        ClientRegistrationRepository clientRegistrationRepository, //
                        OAuth2AuthorizedClientService clientService) {

                OAuth2AuthorizedClientProvider clientProvider = OAuth2AuthorizedClientProviderBuilder.builder() //
                                .authorizationCode() //
                                .refreshToken() //
                                .clientCredentials() //
                                .build();

                AuthorizedClientServiceOAuth2AuthorizedClientManager clientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                                clientRegistrationRepository, clientService);
                clientManager.setAuthorizedClientProvider(clientProvider);

                return clientManager;

        }

}
