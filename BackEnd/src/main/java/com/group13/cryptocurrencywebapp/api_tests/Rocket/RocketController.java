package com.group13.cryptocurrencywebapp.api_tests.Rocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class RocketController {
    @Autowired
    private RocketService rocketService;

    @GetMapping("/complete")
    public Object[] getAllRocketsComplete(){
        return rocketService.findAllRocketsComplete();
    }

    @GetMapping()
    public Rocket[] getAllRockets(){
        return rocketService.findAllRockets();
    }


    
}
