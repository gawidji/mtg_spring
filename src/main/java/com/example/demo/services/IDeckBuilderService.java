package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;

public interface IDeckBuilderService {
	
	DeckCreator inscription (DeckCreator deckBuilder);
	DeckCreator addAdmin (DeckCreator deckBuilder);
	String connection( Map<String, String> request);
	// Boolean isUserAdmin(Long id);
	DeckCreator updateAccount(String email, DeckCreator deckBuilder);
	String deleteDeckBuilder(Long dbID);
	List<DeckCreator> getDeckBuildersByFilter(String pseudo, String email, List<UserActivity> activities);
	List<DeckCreator> getAdmins(String pseudo, String email);

}
