package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.Color;
import com.example.demo.enums.Format;

public interface DeckRepository extends JpaRepository<Deck, Long> {
	
	Optional<Deck> findByName(String name);
	List<Deck> findByNameContaining(String nom);
	List <Deck> findByDeckBuilder (DeckCreator deckBuilder);
	List<Deck> findByFormat(Format format);
	List<Deck> findByColors (Color color);
	
	@Query("SELECT d FROM Deck d " +
		       "WHERE (:name IS NULL OR d.name LIKE %:name%) " +
		       "AND (:manaCost IS NULL OR d.manaCost < :manaCost) " +
		       "AND (:value IS NULL OR d.value < :value) " +
		       "AND (:formats IS NULL OR d.format IN :formats) " +
		       "AND (:colors IS NULL OR d.colors IN :colors) " +
		       "AND (:isPublic IS NULL OR d.isPublic = :isPublic)")
				List<Deck> findByOptionalAttribute(
				@Param("name") String name,
				@Param("manaCost") Long manaCost,
				@Param("value") Float value,
				@Param("formats") List<Format> formats,
				@Param("colors") List<Color> colors,
				@Param("isPublic") boolean isPublic
				);
	
	// Tentative de query avec des list à reproduire dans cardRepository si fonctionne



}
