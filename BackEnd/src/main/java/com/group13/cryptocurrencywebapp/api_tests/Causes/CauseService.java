package com.group13.cryptocurrencywebapp.api_tests.Causes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.web_entity.benevity.causes.Cause;

@Service
public class CauseService {

    @Autowired
    @Qualifier("benevityClient")
    private WebClient webclient = WebClient.create();

    public Cause findAllCauses() {
        System.out.println("request started");

        ResponseEntity<Cause> response = webclient.get()
                .uri("/search/causes?q=")
                .retrieve()
                .toEntity(Cause.class)
                .block();

        return response.getBody();
    }
}