package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;

public interface IAuthenticationService {

	DeckCreator inscription (DeckCreator deckBuilder);
	DeckCreator addAdmin (DeckCreator deckBuilder);
	String connexion( Map<String, String> request);
	// String connexion(DeckCreator db);
	

}
