package com.group13.cryptocurrencywebapp.api_tests.Causes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class CauseController {
    @Autowired
    private CauseService causeService;
    
    @GetMapping("/tests/allcauses")
    public Object[] getAllCausesComplete(){
        return causeService.findAllCausesComplete();
    }

    @GetMapping("/tests/causes")
    public Cause[] getAllCauses(){
        return causeService.findAllCauses();
    }
}
