package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Color;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.DeckRepository;

@Service
public class CardService implements ICardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private DeckRepository deckRepository;

	//Rajouter les attributs image dés que possible dans les méthodes add
	
	@Override
	public Card addCard(Card card) {
		Card cd = Card.builder().name(card.getName()).text(card.getText())
				.formats(card.getFormats()).colors(card.getColors()).manaCost(card.getManaCost())
				.type(card.getType()).rarity(card.getRarity()).edition(card.getEdition()).value(card.getValue())
				.build();
		return cardRepository.save(cd);
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
	
	@Override
	public List<Format> getCardFormats(Long cardID) {
		Optional<Card> card = cardRepository.findById(cardID);
		if(card.isPresent()) {
		return card.get().getFormats();
		}
		throw new RuntimeException("Carte non trouvée");
	}
	
	@Override
	public List<Card> getCardsByFilter (String name,  Long manaCostMin, Long manaCostMax, 
			Float valueMin,	Float valueMax, List<Format> formats, List<Color> colors, List<CardType> types, List<Rarity> rarities, List<Edition> editions) {
		return cardRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax, formats, colors, types, rarities, editions);
	}
	// Filtre les cartes par tous les attributs potentiellement entrés en paramètres
		
	
	
}
