package com.example.demo.services;

import java.util.List;
import java.util.Optional;

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
	public List<Card> getCardsByFilter (String name, Long manaCost, Float value, Format format, Color color, CardType type, Rarity rarity, Edition edition) {
		return cardRepository.findByOptionalAttribute(name, manaCost, value, format, color, type, rarity, edition);
	}
	// Filtre les cartes par tous les attributs potentiellement entrés en paramètre
		
	
	
}
