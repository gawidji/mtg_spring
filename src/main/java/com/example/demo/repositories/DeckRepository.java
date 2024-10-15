package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Deck;
import com.example.demo.enums.Format;

public interface DeckRepository extends JpaRepository<Deck, Long> {
	
	Optional<Deck> findByNom(String nom);
	// List <Deck> findByCommander (String commander);
	// List <Deck> findByBuilder (String builder);
	List<Deck> findByFormat(Format format);
	// Filtrage par couleur manquant 


}
