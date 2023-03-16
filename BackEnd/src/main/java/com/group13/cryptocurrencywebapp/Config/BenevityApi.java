package com.group13.cryptocurrencywebapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BenevityApi {

    static String API_ENDPOINT = "https://api.benevity-staging.org";

    @Bean
    WebClient benevityClient(OAuth2AuthorizedClientManager clientManager) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = //
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientManager);
        oauth2.setDefaultClientRegistrationId("benevity");
        return WebClient.builder() //
                .baseUrl(API_ENDPOINT) //
                .apply(oauth2.oauth2Configuration()) //
                .build();
    }

    // @Bean
    // BenevityApi client(WebClient webclient) {
    // HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(
    // new WebClientAdapter(webclient)) //
    // .build();
    // return factory.createClient(BenevityApi.class);

    // }

}
