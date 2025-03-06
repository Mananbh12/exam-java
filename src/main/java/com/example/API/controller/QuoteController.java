package com.example.API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.model.Quote;
import com.example.API.service.QuoteService;

@RestController
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/getQuote")
    public Quote getQuote() {
        return quoteService.getRandomQuote();
    }
}
