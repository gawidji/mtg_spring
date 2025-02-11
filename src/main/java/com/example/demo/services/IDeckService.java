package com.example.demo.services;

import java.util.List;
import java.util.Set;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import com.example.demo.form.FormDeck;
import com.example.demo.register.GetCard;
import com.example.demo.register.GetDeck;

public interface IDeckService {
	
	// Methodes f_all
		
		List<GetDeck> getTopDecks();
		List<GetDeck> getDecksByFilter(String name, Long manaCostMin, Long manaCostMax, 
		Float valueMin,	Float valueMax, List<EnumFormat> formats, List<EnumColor> colors);
		GetDeck getDeckById(Long deckID);
		List<GetCard> getCardsOnDeckById(Long deckID);
	
	// Methodes f_user
	
	Set<GetDeck> getDecksByUser(DeckCreator dbuilder);
	
	Deck addDeckWithForm (DeckCreator dbuilder, FormDeck deckRegister );
	
	
	String deleteDeck(Long deckID);
	Deck updateDeck(Long deckID, Deck deckUpdate);
	
	List<Card> getCardsByFilterForDeck(Long deckId, String name, Long manaCostMin, Long manaCostMax, Float valueMin, Float valueMax, List<CardType> types,
									   String legendary, List<EnumRarity> rarities, List<EnumEdition> editions);
	/*
	List<Card> getCommanderByFilterForDeck(Long deckId, String name, Long manaCostMin, Long manaCostMax, Float valueMin,
			Float valueMax, List<EnumRarity> rarities, List<EnumEdition> editions);
		 */
	Deck addCardOnDeck(Long cardId, Long deckId);
	Deck addCommanderOnDeck(Long cardId, Long deckId);


	String deleteCardOnDeck(Long cardId, Long deckId);
	String deleteCommanderOnDeck(Long deckID);
	
	String publishDeck(Long deckID);
	String privateDeck(Long deckID);
	
	Float getDeckValue(Long deckID);
	Float getDeckManaCost(Long deckID);
	
	
	




	
	
	
	
	
	
	

}
