package com.group13.cryptocurrencywebapp.api_tests.Causes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.Config.BenevityApi;

@Service
public class CauseService {
    // @Autowired
    // private RestTemplate template = new RestTemplate();

    @Autowired
    private BenevityApi webclient;

    public Object[] findAllCausesComplete() {
        return oAuth2RestTemplate.getForObject("https://api.benevity-staging.org/search/causes?q=", Object[].class);
    }

    public Cause[] findAllCauses() {
        return oAuth2RestTemplate.getForObject("https://api.benevity-staging.org/search/causes?q=", Cause[].class);
    }
}
