package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.DeckBuilder;

@Repository
public interface DeckBuilderRepository extends JpaRepository<DeckBuilder, Long> {
	Optional<DeckBuilder> findByPseudo(String pseudo);
	Optional<DeckBuilder> findByEmail(String email);
	// Optional<DeckBuilder> findByIsAdmin();
}
