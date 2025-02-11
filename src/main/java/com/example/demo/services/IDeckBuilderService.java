package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;
import com.example.demo.register.GetCard;
import com.example.demo.register.GetDeck;
import com.example.demo.register.GetDeckBuilder;

public interface IDeckBuilderService {
	
	DeckCreator updateAccount(String email, DeckCreator deckBuilder);
	String deleteDeckBuilder(Long dbID);
	List<DeckCreator> getDeckBuildersByFilter(String pseudo, String email, List<UserActivity> activities);
	
	void likeCard(DeckCreator user, Long cardId);
	void dislikeCard(DeckCreator user, Long cardId);
	List<GetCard> getCardLiked(DeckCreator user);
	
	void likeDeck (DeckCreator user, Long deckID);
	void dislikeDeck (DeckCreator user, Long deckID);
	List<GetDeck> getDeckLiked (DeckCreator user);
	GetDeckBuilder getDeckBuilder(DeckCreator deckBuilder);
}
