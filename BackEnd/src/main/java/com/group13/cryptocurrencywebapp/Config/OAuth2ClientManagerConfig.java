package com.group13.cryptocurrencywebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

/**
 * <pre>
 * Class Name: OAuth2ClientManagerConfig
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that configures bean model for oauth2 security definitions.
 * For this POC Benevity API calls are uing oatuh2
 * oauth2 parameters are being read from memory and being defined in the
 * application.properties env file
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
public class OAuth2ClientManagerConfig {

        /**
         * Bean model configuration used by Spring Boot to configure general oauth2
         * configuration.
         * An OAuth2AuthorizedClientManager type of object is utilized to intialize
         * ClientRegistrationRepository and OAuth2AuthorizedClientService
         * An OAuth2AuthorizedClientProvider type of object is utilized to build
         * parameters for client configuration Strategy. Refresh token is informe to
         * automatically refresh token after it is expired.
         * An AuthorizedClientServiceOAuth2AuthorizedClientManager is used to allow
         * oauth2 usage by automatic processes and user requests.
         * 
         * @param clientRegistrationRepository object that holds all client
         *                                     registrations definided in the
         *                                     application.properties env file
         * @param clientService                interface object allows client connectios
         *                                     to use ouath2
         * @return OAuth2AuthorizedClientManager object with full oauth2 configuration
         *         to be injected by Spring Boot
         */
        @Bean
        public OAuth2AuthorizedClientManager clientManager(
                        ClientRegistrationRepository clientRegistrationRepository,
                        OAuth2AuthorizedClientService clientService) {

                OAuth2AuthorizedClientProvider clientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                                .authorizationCode()
                                .refreshToken()
                                .clientCredentials()
                                .build();

                AuthorizedClientServiceOAuth2AuthorizedClientManager clientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                                clientRegistrationRepository, clientService);
                clientManager.setAuthorizedClientProvider(clientProvider);

                return clientManager;

        }

}
