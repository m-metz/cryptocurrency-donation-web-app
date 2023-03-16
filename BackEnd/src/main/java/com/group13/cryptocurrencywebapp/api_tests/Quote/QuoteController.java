package com.group13.cryptocurrencywebapp.api_tests.Quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tests/quote")
public class QuoteController {
    
    @Autowired
    private QuoteService  quoteService;

    @GetMapping()
    public Quote[] getQuote(){
        //return new Quote();
        return quoteService.findQuote();
    }

    


}
