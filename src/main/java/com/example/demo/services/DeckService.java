package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeckBuilder;
import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.repositories.DeckRepository;


@Service
public class DeckService implements IDeckService {
	
	@Autowired
	DeckBuilderRepository deckBuilderRepository;
	
	@Autowired
	DeckRepository deckRepository;
	
	public Deck addDeck(Long userId, Deck deck) {
		Optional<DeckBuilder> dbuilder = deckBuilderRepository.findById(userId);
		
		if(dbuilder.isPresent()) {
		// Deck d = Deck.builder()
		// return deckRepository.save(d);
			return null;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	// Créé un deck pour un user 
	
	@Override
	public List<Deck> getDeckById(Long dbID) {
		Optional<DeckBuilder> dbuilder = deckBuilderRepository.findById(dbID);
		
		if(dbuilder.isPresent()) {
			// return dbuilder.get().getDecks();
			return null;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	// Récupère les decks pour un user
	
	public float getDeckValue(Long deckID) {
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		int deckValue = 0;
		
		if(deck.isPresent()) {
			 List<Card> cardsDeck = deck.get().getCartes();
			 for (Card cards : cardsDeck) {
				 float cardValue = cards.getValue();
				 deckValue += cardValue;
				
			}
		return deckValue;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	// Donne le cout total d'un deck 
	
	
	public float getDeckManaCost(Long deckID) {
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		int deckManaCost = 0;
		float deckManaCostMoy = 0;
		
		if(deck.isPresent()) {
			 List<Card> cardsDeck = deck.get().getCartes();
			 for (Card cards : cardsDeck) {
				 int cardValue = cards.getCoutMana();
				 if(cardValue >= 0 ) {
					 deckManaCost += cardValue;	
				 	deckManaCostMoy = deckManaCost / cardsDeck.toArray().length;
				 }
			}
			
		return deckManaCostMoy;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	
	

}
