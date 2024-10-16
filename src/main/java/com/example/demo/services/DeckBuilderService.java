package com.example.demo.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeckBuilder;
import com.example.demo.enums.UserRole;
import com.example.demo.repositories.DeckBuilderRepository;

@Service
//Obligatoire pour rendre le fichier visible
public class DeckBuilderService implements IDeckBuilderService  {
	
	@Autowired
	private DeckBuilderRepository deckBuilderRepository;

	@Override
	public DeckBuilder inscription(DeckBuilder deckBuilder) {
		DeckBuilder db = DeckBuilder.builder().pseudo(deckBuilder.getPseudo()).email(deckBuilder.getEmail()).password(deckBuilder.getPassword()).avatar(deckBuilder.getAvatar()).role(UserRole.USER.toString())
				.build();
		return deckBuilderRepository.save(db);
	}
	// Méthode d'inscription d'un utilisateur

	@Override
	public DeckBuilder addAdmin(DeckBuilder deckBuilder) {
		DeckBuilder db = DeckBuilder.builder().pseudo(deckBuilder.getPseudo()).email(deckBuilder.getEmail()).password(deckBuilder.getPassword()).avatar(deckBuilder.getAvatar()).role(UserRole.ADMIN.toString()).build();
		return deckBuilderRepository.save(db);
	}

	@Override
	public String connection( Map<String, String> request) {

		String email = request.get("email");
        String password = request.get("password");
        
        Optional<DeckBuilder> deckBuilder = deckBuilderRepository.findByEmail(email);
        
        if(deckBuilder.isPresent()) {
        	if(deckBuilder.get().getPassword().equals(password)) {
        		return "Connexion réussie";
        	}
        }
        return "Nom d'utilisateur ou mot de passe incorrect";
	}

	@Override
	public Boolean isUserAdmin(Long id) {
		Optional<DeckBuilder> db = deckBuilderRepository.findById(id);
		
		if(db.isPresent()) {
			if(db.get().getRole().equals(UserRole.ADMIN.toString())) {
			return true;
			}
			return false;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}

}
