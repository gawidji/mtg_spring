package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Format;
import com.example.demo.entities.Deck;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.ColorRepository;
import com.example.demo.repositories.DeckRepository;
import com.example.demo.repositories.FormatRepository;

@Service
public class CardService implements ICardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private FormatRepository formatRepository;

	//Rajouter les attributs image dés que possible dans les méthodes add
	
	
	@Override
	public Card addCard(Card card, List<Color> cardColors, List<Format> cardFormats ) {
		
		for (Color color : cardColors) {
			
			card.getColors().add(colorRepository.findByName(color.getName()));
		}
		
		for (Format format : cardFormats) {
			
			card.getFormats().add(formatRepository.findByName(format.getName()));
		}
		
		return cardRepository.save(card);
	}
	
	
	
	
	@Override 
	public Card updateCard (Long cardID, Card cardUpdate) {
		
		Optional<Card> card = cardRepository.findById(cardID);
		
		if(card.isPresent()) {
			
			Card newCard = card.get();	
				
			if( cardUpdate.getName() != null) {
				newCard.setName(cardUpdate.getName()); }
			if(cardUpdate.getText() != null) {
				newCard.setText(cardUpdate.getText()); }
			if( cardUpdate.getManaCost() != null) {
				newCard.setManaCost(cardUpdate.getManaCost()); }
			if( cardUpdate.getValue() != null) {
				newCard.setValue(cardUpdate.getValue()); }
			if( cardUpdate.getFormats() != null) {
				newCard.setFormats(cardUpdate.getFormats()); }
			if( cardUpdate.getEdition() != null) {
				newCard.setEdition(cardUpdate.getEdition()); }
			if( cardUpdate.getRarity() != null) {
				newCard.setRarity(cardUpdate.getRarity()); }
			if(cardUpdate.getImage() != null) {
				newCard.setImage(cardUpdate.getImage()); }
			
			return cardRepository.save(newCard);
		}
		throw new RuntimeException("Deck non trouvé");
	}
	
	@Override
	public String deleteCard(Long cardID) {	
		
		Optional<Card> card = cardRepository.findById(cardID); 
		
		if(card.isPresent()) {
			
			Card cardFind = card.get();
					
			for (Deck deckTarget : cardFind.getDecks() )
			{
				deckTarget.getCartes().remove(cardFind);
				deckRepository.save(deckTarget);
			}
					
			cardRepository.deleteById(cardID);
			return "Carte numéro " + cardID + " supprimée";
		}
		throw new RuntimeException("Carte non trouvée");
	}
	
	@Override
	public List<Card> getAllCards() {
		return cardRepository.findAll();
	}
	/*
	@Override
	public List<EnumFormat> getCardFormats(Long cardID) {
		Optional<Card> card = cardRepository.findById(cardID);
		if(card.isPresent()) {
		return card.get().getFormats();
		}
		throw new RuntimeException("Carte non trouvée");
	}
	*/
	
	@Override
	public List<Card> getCardsByFilter (String name,  Long manaCostMin, Long manaCostMax, 
			Float valueMin,	Float valueMax, List<EnumFormat> formats, List<EnumColor> colors, 
			List<CardType> types, List<EnumRarity> rarities, List<EnumEdition> editions) {
		
		List<Color> colorsEntities = new ArrayList<>();	
		if(colors != null) {
			for (EnumColor color : colors) {	
				colorsEntities.add(colorRepository.findByName(color));
			}
		}
		else {
			colorsEntities = null;
		}
		
		
		List<Format> formatsEntities = new ArrayList<>();	
		if(formats != null) {
			for (EnumFormat format : formats) {
				formatsEntities.add(formatRepository.findByName(format));
			}
		}
		else {
			formatsEntities = null;
		}
		
		return cardRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax, types,
				rarities, editions, colorsEntities, formatsEntities);
	}
	// Filtre les cartes par tous les attributs potentiellement entrés en paramètres
	
	
	@Override
	public List<Card> findByColors(List<EnumColor> colors) {
		
		List<Color> colorsFind = new ArrayList<>();	 

		for (EnumColor color : colors) {
			
			colorsFind.add(colorRepository.findByName(color));
		}
		
		return cardRepository.findByColorsIn(colorsFind);
	}
	
	
	@Override
	public List<Card> findByFormats(List<EnumFormat> formats) {
		
		List<Format> formatsFind = new ArrayList<>();	 

		for (EnumFormat format : formats) {
			
			formatsFind.add(formatRepository.findByName(format));
		}
		
		return cardRepository.findByFormatsIn(formatsFind);
	}
		
		
	
	
}
