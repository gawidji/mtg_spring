package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Card;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.Edition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.Rarity;

public interface CardRepository extends JpaRepository<Card, Long> {
	/*
	Optional<Card> findByName(String nom);
	List<Card> findByNameContaining(String nom);
	List<Card> findByFormats(Format formats);
	List<Card> findByManaCost (int manaCost);
	List<Card> findByColors (Color color);
	List<Card> findByType (CardType type);
	List<Card> findByRarity(Rarity rarete);
	List<Card> findByEdition (Edition edition);
	*/
	// Potentiellement ajouter un filtre pour la value
	
	
	@Query("SELECT c FROM Card c " +
		       "WHERE (:name IS NULL OR c.name LIKE %:name%) " +
		       "AND (:manaCostMin IS NULL OR c.manaCost > :manaCostMin) " +
		       "AND (:manaCostMax IS NULL OR c.manaCost < :manaCostMax) " +
		       "AND (:valueMin IS NULL OR c.value > :valueMin) " +
		       "AND (:valueMax IS NULL OR c.value < :valueMax) " +
		       "AND (:types IS NULL OR c.type IN :types) " +
		       "AND (:rarities IS NULL OR c.rarity IN :rarities) " +
		       "AND (:editions IS NULL OR c.edition IN :editions) ")
		List<Card> findByOptionalAttribute(
				@Param("name") String name,
				@Param("manaCostMin") Long manaCostMin,
				@Param("manaCostMax") Long manaCostMax,
				@Param("valueMin") Float valueMin,
				@Param("valueMax") Float valueMax,
		        @Param("types") List<CardType> types,
		        @Param("rarities") List<Rarity> rarities,
		        @Param("editions") List<Edition> editions);
	
	/*
	@Query("SELECT c FROM Card c JOIN c.oneOrManyColors Colors WHERE Colors.name IN :colors")
	List<Card> findByColor(@Param("colors") List<EnumColor> colors);
	*/

}
