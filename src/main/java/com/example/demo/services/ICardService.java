package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Card;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Color;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;

public interface ICardService {
	
	public Card addCard(Card card);
	public String deleteCard(Long cardID);
	public List<Card> getAllCards();
	public List<Format> getCardFormats(Long cardID);
	List<Card> getCardsByFilter(String name, Long manaCost, Float value, Format format, Color color, CardType type, Rarity rarity, Edition edition);



}
