package com.group13.cryptocurrencywebapp.api_tests.Quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class QuoteController {
    
    @Autowired
    private QuoteService  quoteService;

    @GetMapping("/tests/quote")
    public Quote getQuote(){
        return quoteService.findQuote();
    }


}
