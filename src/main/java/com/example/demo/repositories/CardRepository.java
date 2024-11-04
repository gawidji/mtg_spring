package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Card;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Color;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	Optional<Card> findByName(String nom);
	List<Card> findByNameContaining(String nom);
	List<Card> findByFormats(Format formats);
	List<Card> findByManaCost (int manaCost);
	List<Card> findByColors (Color color);
	List<Card> findByType (CardType type);
	List<Card> findByRarity(Rarity rarete);
	List<Card> findByEdition (Edition edition);
	// Potentiellement ajouter un filtre pour la value
	
	@Query("SELECT c FROM Card c " +
		       "WHERE (:name IS NULL OR c.name LIKE %:name%) " +
		       "AND (:manaCost IS NULL OR c.manaCost < :manaCost) " +
		       "AND (:value IS NULL OR c.value < :value) " +
		       "AND (:format IS NULL OR c.formats = :format) " +
		       "AND (:color IS NULL OR c.colors = :color) " +
		       "AND (:type IS NULL OR c.type = :type) " +
		       "AND (:rarity IS NULL OR c.rarity = :rarity) " +
		       "AND (:edition IS NULL OR c.edition = :edition) ")
		List<Card> findByOptionalAttribute(
				@Param("name") String name,
				@Param("manaCost") Long manaCost,
				@Param("value") Float value,
		        @Param("format") Format format,
		        @Param("color") Color color,
		        @Param("type") CardType type,
		        @Param("rarity") Rarity rarity,
		        @Param("edition") Edition edition);

	

}
