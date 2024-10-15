package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Card;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	Optional<Card> findByNom(String nom);
	List<Card> findByFormat(Format format);
	List<Card> findByRarete(Rarity rarete);
	List<Card> findByEdition (Edition edition);
	List<Card> findByCoutMana (int coutMana);
	// Filtrage par couleur manquant 

}
