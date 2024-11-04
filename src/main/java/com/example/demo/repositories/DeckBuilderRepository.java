package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.DeckCreator;


@Repository
public interface DeckBuilderRepository extends JpaRepository<DeckCreator, Long> {
	Optional<DeckCreator> findByPseudo(String pseudo);
	Optional<DeckCreator> findByEmail(String email);
	// Optional<DeckBuilder> findByIsAdmin();
}
