package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Deck;

public interface IDeckService {
	
	public Deck addDeck(Long userId, Deck deck);
	public List<Deck> getDeckById(Long dbID);
	public float getDeckValue(Long deckID);
	public float getDeckManaCost(Long deckID);
}
