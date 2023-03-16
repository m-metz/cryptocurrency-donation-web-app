package com.group13.cryptocurrencywebapp.api_tests.Rocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RocketService {
    @Autowired
    WebClient webClient;
    
    public Object[] findAllRocketsComplete() {
        return webClient.get().uri("http://localhost:8080/api/v1/rocket").retrieve().bodyToMono(Object[].class).block();
    }

    public Rocket[] findAllRockets() {
        return webClient.get().uri("http://localhost:8080/api/v1/rocket").retrieve().bodyToMono(Rocket[].class).block();
    }
    
}
