package com.group13.cryptocurrencywebapp.api_tests.Film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmService {
    
    @Autowired
    private RestTemplate template = new RestTemplate();

    public Object[] findAllFilmsComplete(){
        return template.getForObject("https://ghibliapi.herokuapp.com/films/", Object[].class);
    }

    public Film[] findAllFilms(){
        return template.getForObject("https://ghibliapi.herokuapp.com/films/", Film[].class);

    }
}
