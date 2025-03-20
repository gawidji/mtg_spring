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
import com.example.demo.form.FormCard;
import com.example.demo.register.GetCard;

public interface ICardService {
	
	List<GetCard> getTopCards();
	List<GetCard> getTop3Cards();
	
	Card addCard(Card card, List<Color> cardColors, List<Format> cardFormats);
	Card addCardTest(FormCard cardRegister);
	Card updateCard(Long cardID, Card cardUpdate);
	String deleteCard(Long cardID);
	
	
	List<GetCard> getCardsByFilter(String name, Long manaCostMin, Long manaCostMax, Float valueMin, Float valueMax,
			List<EnumFormat> formats, List<EnumColor> colors, List<CardType> types, String legendary,
			List<EnumRarity> rarities, List<EnumEdition> editions);
	
	List<GetCard> getCardsForDeck(Long DeckID, String name, Long manaCostMin, Long manaCostMax, Float valueMin,
			Float valueMax, List<EnumColor> colorFilter, List<CardType> types, String legendary,
			List<EnumRarity> rarities, List<EnumEdition> editions);
	
	GetCard getCardById(Long cardId);

	
	List<Card> findByColors(List<EnumColor> colors);
	List<Card> findByFormats(List<EnumFormat> formats);
	
	

	
	




}
