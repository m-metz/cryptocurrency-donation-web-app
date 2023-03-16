package com.group13.cryptocurrencywebapp.api_tests.Causes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cause")
public class CauseController {
    @Autowired
    private CauseService causeService;

    @GetMapping("/tests/allcauses")
    public List<Cause> getAllCausesComplete() {
        return causeService.findAllCauses();
    }
}
// @GetMapping("/tests/causes")
// public Cause[] getAllCauses(){
// return causeService.findAllCauses();
// }
// }
