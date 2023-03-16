package com.group13.cryptocurrencywebapp.api_tests.Quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {
    @Autowired

    //private RestTemplate template = new RestTemplate();

    public Quote findQuote(){

        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
        .uri("localhost:8080/api/random")
        .retrieve();

        Quote responseBody = responseSpec.bodyToMono(Quote.class).block();

        //String responseBody = responseSpec.bodyToMono(String.class).block();
        return responseBody;
        
        //Quote q = template.getForObject("localhost:8080/api/random", Quote.class);
        //return q;

    }
}
