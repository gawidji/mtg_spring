package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Card;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Color;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;

public interface ICardService {
	
	Card addCard(Card card);
	Card updateCard(Long cardID, Card cardUpdate);
	String deleteCard(Long cardID);
	List<Card> getAllCards();
	List<Format> getCardFormats(Long cardID);
	List<Card> getCardsByFilter(String name, Long manaCostMin, Long manaCostMax, Float valueMin, Float valueMax,
			List<Format> formats, List<Color> colors, List<CardType> types, List<Rarity> rarities,
			List<Edition> editions);



}
