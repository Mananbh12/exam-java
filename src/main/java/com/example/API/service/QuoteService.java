package com.example.API.service;

import com.example.API.model.Quote;
import com.example.API.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final Random random = new Random();

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        
        if (quotes.isEmpty()) {
            throw new RuntimeException("Aucune citation disponible dans la base de données");
        }

        int randomIndex = random.nextInt(quotes.size());
        return quotes.get(randomIndex);
    }
}