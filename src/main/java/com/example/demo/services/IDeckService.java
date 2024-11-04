package com.example.demo.services;

import java.util.List;
import java.util.Set;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Color;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;

public interface IDeckService {
	
	// Methodes f_user
	
	Deck addDeck(Long userId, Deck deck);
	String deleteDeck(Long deckID);
	Deck updateDeck(Long deckID, Deck deckUpdate);

	
	List<Card> getCardsByFilterForDeck(Long deckId, String name, Long manaCost, Float value, CardType type, Rarity rarity, Edition edition);
	List<Card> getCommanderByFilterForDeck(Long deckId, String name, Long manaCost, Float value, Rarity rarity, Edition edition);

	Deck addCardOnDeck(Long cardId, Long deckId);
	Deck addCommanderOnDeck(Long cardId, Long deckId);
	String deleteCardOnDeck(Long cardId, Long deckId);
	String deleteCommanderOnDeck(Long deckID);
	
	String publishDeck(Long deckID);
	String privateDeck(Long deckID);
	
	Set<Deck> getDeckByUser(Long dbID);
	
	Float getDeckValue(Long deckID);
	Float getDeckManaCost(Long deckID);
	
	
	// Methodes f_all
	
	List<Deck> getDecksByFilter(String name, List<Format> formats, List<Color> colors);
	
	

}
