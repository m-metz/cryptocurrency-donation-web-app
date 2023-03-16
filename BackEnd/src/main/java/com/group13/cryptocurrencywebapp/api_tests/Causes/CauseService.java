package com.group13.cryptocurrencywebapp.api_tests.Causes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.Config.BenevityApi;

@Service
public class CauseService {
    // @Autowired
    // private RestTemplate template = new RestTemplate();

    @Autowired
    private WebClient webclient = WebClient.create();

    // public Object[] findAllCausesComplete() {
    // return
    // oAuth2RestTemplate.getForObject("https://api.benevity-staging.org/search/causes?q=",
    // Object[].class);
    // }

    public List<Cause> findAllCauses() {
        System.out.println("request started");

        ResponseEntity<List<Cause>> response = webclient.get()
                .uri("/search/causes?q=")
                .retrieve()
                .toEntityList(Cause.class)
                .block();

        return response.getBody();
    }
}