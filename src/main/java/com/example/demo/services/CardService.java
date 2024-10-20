package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Card;
import com.example.demo.enums.CardType;
import com.example.demo.repositories.CardRepository;

@Service
public class CardService implements ICardService {

	@Autowired
	private CardRepository cardRepository;

	//Rajouter les attributs image dés que possible dans les méthodes add
	
	@Override
	public Card addCard(Card card) {
		Card cd = Card.builder().nom(card.getNom()).format(card.getFormat()).couleur(card.getCouleur()).coutMana(card.getCoutMana())
				.type(card.getType()).rarete(card.getRarete()).edition(card.getEdition()).value(card.getValue())
				.build();
		return cardRepository.save(cd);
	}
		
	
}
