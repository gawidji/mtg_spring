package com.example.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data


@Builder
// Permet d'intancier un objet de la class plus simplement dans BuilderService

@Entity
// Map la classe avec une data_base
// relie les attributs de la classe et les annotations qui sont placées directement au-dessus d'eux


@Table(name = "deck_builder")


public class DeckBuilder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pseudo", length = 25, nullable = false, unique = true)
	private String pseudo;
	
	@Column(name = "email", length = 32, nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", length = 32, nullable = false)
	private String password;
	
	@Column(name = "date_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateInscription;
	
	@Column(name ="avatar", length = 500 )
	private String avatar;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	
	@OneToMany(mappedBy = "deckBuilder", cascade = CascadeType.ALL)
	private Set<Deck> decks;
	// Relation one - user & many - decks
	//mappé par "user" qui se retrouve dans la classe Deck pour générer une relation bi-directionnelle 
	// CascadeTpe.all pour que les opérations de la classe User (persist, remove, merge, etc) soient aussi effectués sur la classe Deck
	
	
	
	
	public DeckBuilder() {
		this.decks = new HashSet<>();
	}
	
	
	public DeckBuilder(Long id, String pseudo, String email, String password, Date dateInscription, String avatar, String role,
			Set<Deck> decks) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.dateInscription = dateInscription;
		this.avatar = avatar;
		this.role = role;
		this.decks = new HashSet<>();
	}
	

}
