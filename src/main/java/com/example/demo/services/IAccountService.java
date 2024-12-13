package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;

public interface IAccountService {
	
	DeckCreator updateAccount(String email, DeckCreator deckBuilder);
	String deleteDeckBuilder(Long dbID);
	List<DeckCreator> getDeckBuildersByFilter(String pseudo, String email, List<UserActivity> activities);

}
