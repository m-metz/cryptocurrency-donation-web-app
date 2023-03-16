package com.group13.cryptocurrencywebapp.api_tests.Quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class QuoteService {
    @Autowired
    WebClient webClient;
    //private RestTemplate template = new RestTemplate();

    public Quote[] findQuote(){

        // webClient = WebClient.create();

        // WebClient.ResponseSpec responseSpec = webClient.get()
        // .uri("localhost:8080/api/random")
        // .retrieve();

        //Quote responseBody = responseSpec.bodyToMono(Quote.class).block();
        //String test = webClient.get().uri("localhost:8080/api/random").retrieve().bodyToMono(String.class).block();
        //System.out.println(test);
        
        
        return webClient.get().uri("localhost:8080/api/random").retrieve().bodyToMono(Quote[].class).block();

        //return responseBody;

        //return new Quote();
        

        //String responseBody = responseSpec.bodyToMono(String.class).block();
        //Quote q = template.getForObject("localhost:8080/api/random", Quote.class);
        //return q;

    }
}
