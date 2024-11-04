package com.example.demo.services;

import java.util.Map;

import com.example.demo.entities.DeckCreator;

public interface IDeckBuilderService {
	
	DeckCreator inscription (DeckCreator deckBuilder);
	DeckCreator addAdmin (DeckCreator deckBuilder);
	String connection( Map<String, String> request);
	public Boolean isUserAdmin(Long id);
	DeckCreator updateProfil(String email, DeckCreator deckBuilder);

}
