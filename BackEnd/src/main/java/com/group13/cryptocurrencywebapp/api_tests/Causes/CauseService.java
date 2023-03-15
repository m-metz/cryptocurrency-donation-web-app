package com.group13.cryptocurrencywebapp.api_tests.Causes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CauseService {
    @Autowired
    private RestTemplate template = new RestTemplate();

    public Object[] findAllCausesComplete(){
        return template.getForObject("https://api.benevity-staging.org/search/causes?q=", Object[].class);
    }

    public Cause[] findAllCauses(){
        return template.getForObject("https://api.benevity-staging.org/search/causes?q=", Cause[].class);
    }
}
