package com.example.demo.services;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;
import com.example.demo.enums.UserRole;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.security.ConfigurePasswordEncoder;
import com.example.demo.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
// Le constructor est nécessaire dans cette class pour éviter les dépendances cycliques
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
	
	// Les méthodes register et login se retrouvent ici pour éviter des bugs liés aux dépendances cycliques
	
	@Autowired
	private DeckBuilderRepository deckBuilderRepository;
	
	@Autowired
	private ConfigurePasswordEncoder passwordEncoder;
	// Appel de ConfigurePasswordEncoder pour avoir notre encodage personalisé plutot que le PasswordEncoder d'origine
	
	@Autowired
	private  JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	// Méthode d'auth conçue dans la securityConifg
	
	@Override
	public DeckCreator inscription(DeckCreator deckBuilder) {
	
		DeckCreator db = DeckCreator.builder().pseudo(deckBuilder.getPseudo()).email(deckBuilder.getEmail()).password(passwordEncoder.encode(deckBuilder.getPassword()))
				.avatar(deckBuilder.getAvatar()).dateSign((LocalDate.now()))
				.activity(UserActivity.VIEWVER).role(UserRole.USER)
				.build();
		
		return deckBuilderRepository.save(db);
	}
	// Méthode d'inscription d'un utilisateur
	
	@Override
	public DeckCreator addAdmin(DeckCreator deckBuilder) {
	
		
		DeckCreator db = DeckCreator.builder().pseudo(deckBuilder.getPseudo()).email(deckBuilder.getEmail()).password(passwordEncoder
				.encode(deckBuilder.getPassword())).avatar(deckBuilder.getAvatar()).role(UserRole.ADMIN)
				.build();
		
		return deckBuilderRepository.save(db);
	}
	
	@Override
	public String connexion(Map<String, String> request) {
		String email = request.get("email");
       String password = request.get("password");
       
       Optional<DeckCreator> user = deckBuilderRepository.findByEmail(email);
       
       if(user.isPresent()) {
	       DeckCreator userFind = user.get();
	
	       if(passwordEncoder.matches(password, userFind.getPassword())) {
	    	   String jwt = jwtService.generateToken(userFind);
	    	   return "Connexion réussie " + jwt;
	    	}
       }
    	   
       	return "echec de la connexion";
       /*
		try {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		return "Vous etes connecté";
		// UsernamePasswordAuthenticationToken implémente une interface authentication 
		// On lui donne des données à encapsuler (email et password) qu'elle va transmettre à authenticationManager
				}
		
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "Nom d'utilisateur ou mot de passe incorrect";
					} 
					*/
	}

}
