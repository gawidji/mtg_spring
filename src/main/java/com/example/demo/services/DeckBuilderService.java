package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;
import com.example.demo.enums.UserRole;
import com.example.demo.repositories.DeckBuilderRepository;

@Service
//Obligatoire pour rendre le fichier visible
public class DeckBuilderService implements IDeckBuilderService  {
	
	@Autowired
	private DeckBuilderRepository deckBuilderRepository;

	@Override
	public DeckCreator inscription(DeckCreator deckBuilder) {
		DeckCreator db = DeckCreator.builder().pseudo(deckBuilder.getPseudo()).email(deckBuilder.getEmail()).password(deckBuilder.getPassword()).avatar(deckBuilder.getAvatar())
				.activity(UserActivity.VIEWVER).role(UserRole.USER)
				.build();
		
		return deckBuilderRepository.save(db);
	}
	// Méthode d'inscription d'un utilisateur

	@Override
	public DeckCreator addAdmin(DeckCreator deckBuilder) {
		DeckCreator db = DeckCreator.builder().pseudo(deckBuilder.getPseudo()).email(deckBuilder.getEmail()).password(deckBuilder.getPassword()).
				avatar(deckBuilder.getAvatar()).role(UserRole.ADMIN)
				.build();
		
		return deckBuilderRepository.save(db);
	}

	@Override
	public String connection( Map<String, String> request) {

		String email = request.get("email");
        String password = request.get("password");
        
        Optional<DeckCreator> deckBuilder = deckBuilderRepository.findByEmail(email);
        
        if(deckBuilder.isPresent()) {
        	if(deckBuilder.get().getPassword().equals(password)) {
        		return "Connexion réussie";
        	}
        }
        return "Nom d'utilisateur ou mot de passe incorrect";
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
	
	/*

	@Override
	public Boolean isUserAdmin(Long id) {
		Optional<DeckCreator> db = deckBuilderRepository.findById(id);
		
		if(db.isPresent()) {
			if(db.get().getRole().equals(UserRole.ADMIN.toString())) {
			return true;
			}
			return false;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	*/
	
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
		return deckBuilderRepository.findByOptionalAttribute(pseudo, email, activities, UserRole.USER) ;
	}
	
	@Override
	public List<DeckCreator> getAdmins(String pseudo, String email) {		
		return deckBuilderRepository.findByOptionalAttribute(pseudo, email, null, UserRole.ADMIN);
	}

}
