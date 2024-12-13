package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;


@Repository
public interface DeckBuilderRepository extends JpaRepository<DeckCreator, Long> {
	Optional<DeckCreator> findByPseudo(String pseudo);
	Optional<DeckCreator> findByEmail(String email);
	
	
	@Query("SELECT d FROM DeckCreator d " +
		       "WHERE (:pseudo IS NULL OR d.pseudo LIKE %:pseudo%) " +
		       "AND (:email IS NULL OR d.email LIKE %:email%) " +
		       "AND (:activities IS NULL OR d.activity IN :activities) ")
				List<DeckCreator> findByOptionalAttribute(
		       @Param("pseudo") String pseudo,
		       @Param("email") String email,
		       @Param("activities") List<UserActivity> activities
		       );
	
}
