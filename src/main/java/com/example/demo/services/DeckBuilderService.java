package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.entities.Format;
import com.example.demo.enums.UserActivity;
import com.example.demo.register.GetCard;
import com.example.demo.register.GetDeck;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.repositories.DeckRepository;
@Service
//Obligatoire pour rendre le fichier visible
public class DeckBuilderService implements IDeckBuilderService, UserDetailsService  {
	
	@Autowired
	private DeckBuilderRepository deckBuilderRepository;
	
	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private CardRepository cardRepository;
		
	
	// La méthode qui va rechercher si le mail entré pendant l'auth correspond à une valeur de la database
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return deckBuilderRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Cette adresse mail ne correpsond à aucun compte DeckBuilder"));
	}
	
	
	
	
	@Override
	public DeckCreator updateAccount (String email, DeckCreator deckBuilderUpdate) {
		
		Optional<DeckCreator> deckBuilderTarget = deckBuilderRepository.findByEmail(email);
		
		if(deckBuilderTarget.isPresent()) {
			
			DeckCreator deckBuilderFind = deckBuilderTarget.get();
			
			if(deckBuilderUpdate.getPseudo() != null) {
				deckBuilderFind.setPseudo(deckBuilderUpdate.getPseudo());
				}
			if(deckBuilderUpdate.getAvatar() != null) {
				deckBuilderFind.setAvatar(deckBuilderUpdate.getAvatar());
			}
			
						
			return deckBuilderRepository.save(deckBuilderFind);
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	
	
	@Override
	public void likeCard (DeckCreator user, Long cardId) {
		Optional<Card> cardOpt = cardRepository.findById(cardId);
		if(cardOpt.isPresent()) {
		Card card = cardOpt.get();
		user.getCardsLiked().add(card);
		
		deckBuilderRepository.save(user);
		
		card.setLikeNumber((long) card.getDeckBuilders().size());

		cardRepository.save(card);
		
		}
	}
	
	@Override
	public void dislikeCard (DeckCreator user, Long cardId) {
		Optional<Card> cardOpt = cardRepository.findById(cardId);
		if(cardOpt.isPresent()) {
		Card card = cardOpt.get();
		user.getCardsLiked().remove(card);
		deckBuilderRepository.save(user);
		
		card.setLikeNumber((long) card.getDeckBuilders().size());

		cardRepository.save(card);
		}
	}
	
	@Override
	public List<GetCard> getCardLiked (DeckCreator user) {
		List<Card> cards = user.getCardsLiked();
		List<GetCard> cardsReturn = new ArrayList<>();
		
		for (Card card : cards) {
			GetCard testCard = new GetCard();
			testCard.setId(card.getId());
			testCard.setName(card.getName());
			testCard.setImage(card.getImage());
			testCard.setText(card.getText());
			testCard.setManaCost(card.getManaCost());
			testCard.setValue(card.getValue());
			testCard.setType(card.getType());
			testCard.setRarity(card.getRarity());
			testCard.setEdition(card.getEdition());
			
			for (Color color : card.getColors()) {
				testCard.getColors().add(color.getName());
			}	
			for (Format format : card.getFormats()) {
				testCard.getFormats().add(format.getName());
			}	
			cardsReturn.add(testCard);
		}

		return cardsReturn;
	}
	
	
	@Override
	public void likeDeck (DeckCreator user, Long deckID) {
		Optional<Deck> deckOpt = deckRepository.findById(deckID);
		if(deckOpt.isPresent()) {
		Deck deck = deckOpt.get();
		user.getDecksLiked().add(deck);
		deckBuilderRepository.save(user);
		}
	}
	
	@Override
	public void dislikeDeck (DeckCreator user, Long deckID) {
		Optional<Deck> deckOpt = deckRepository.findById(deckID);
		if(deckOpt.isPresent()) {
		Deck deck = deckOpt.get();
		user.getDecksLiked().remove(deck);
		deckBuilderRepository.save(user);
		}
	}
	
	@Override
	public List<GetDeck> getDeckLiked (DeckCreator user) {
		List<Deck> decks = user.getDecksLiked();
		List<GetDeck> decksReturn = new ArrayList<>();
		
		for (Deck deck : decks) {
			GetDeck testDeck = new GetDeck();
			testDeck.setId(deck.getId());
			testDeck.setName(deck.getName());
			testDeck.setDateCreation(deck.getDateCreation());
			testDeck.setImage(deck.getImage());
			testDeck.setFormat(deck.getFormat());
			testDeck.setManaCost(deck.getManaCost());
			testDeck.setValue(deck.getValue());
			testDeck.setDeckBuilder(deck.getDeckBuilder());
			testDeck.setDeckBuilderName(deck.getDeckBuilder().getPseudo());
			testDeck.setCards(deck.getCards());
			
			for (Color color : deck.getColors()) {
				testDeck.getColors().add(color.getName());
			}	
				
			decksReturn.add(testDeck);
		}

		return decksReturn;
	}
	
	// Partie admin
	
	@Override
	public String deleteDeckBuilder(Long dbID) {
		
		Optional<DeckCreator> db = deckBuilderRepository.findById(dbID);

		if(db.isPresent()) {
			DeckCreator dbFind = db.get();
			deckBuilderRepository.deleteById(dbID);
			
			return "Utilisateur  " + dbFind.getPseudo() + ", adresse : " + dbFind.getEmail() +  " supprimé";
		}
		throw new RuntimeException("User non trouvé");
	}

	@Override
	public List<DeckCreator> getDeckBuildersByFilter (String pseudo, String email, List<UserActivity> activities) {
		return deckBuilderRepository.findByOptionalAttribute(pseudo, email, activities) ;
	}
	
	
	

	

}
