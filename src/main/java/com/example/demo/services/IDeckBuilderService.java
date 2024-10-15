package com.example.demo.services;

import java.util.Map;

import com.example.demo.entities.DeckBuilder;

public interface IDeckBuilderService {
	
	DeckBuilder inscription (DeckBuilder deckBuilder);
	DeckBuilder addAdmin (DeckBuilder deckBuilder);
	String connection( Map<String, String> request);

}
