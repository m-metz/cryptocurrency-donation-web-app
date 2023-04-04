package com.group13.cryptocurrencywebapp.api_tests.Causes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.web_entity.benevity.causes.Cause;

@RestController
@RequestMapping(path = "/cause")
public class CauseController {
    @Autowired
    private CauseService causeService;

    @GetMapping("/tests/allcauses")
    public Cause getAllCausesComplete() {
        return causeService.findAllCauses();
    }
}
