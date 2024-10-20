package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repositories.DeckBuilderRepository;

public class ApplicationConfig {
	
    @Autowired
    DeckBuilderRepository deckBuilderRepository;

}
