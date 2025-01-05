package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Format;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import com.example.demo.register.GetCard;

public interface ICardService {
	
	Card addCard(Card card, List<Color> cardColors, List<Format> cardFormats);
	Card updateCard(Long cardID, Card cardUpdate);
	String deleteCard(Long cardID);
	
	List<Card> getAllCards();
	List<GetCard> getCardsByFilter(String name, Long manaCostMin, Long manaCostMax, Float valueMin, Float valueMax,
			List<EnumFormat> formats, List<EnumColor> colors, List<CardType> types, List<EnumRarity> rarities,
			List<EnumEdition> editions);
	GetCard getCardById(Long cardId);

	
	
	List<Card> findByColors(List<EnumColor> colors);
	List<Card> findByFormats(List<EnumFormat> formats);



}
