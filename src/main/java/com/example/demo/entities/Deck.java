package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.example.demo.enums.Format;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "deck")

public class Deck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom", length = 25, nullable = false, unique = false)
	private String nom;
	
	@Enumerated(EnumType.STRING)
	private Format format;
	
	@Column(name = "couleur", length = 50, nullable = false, unique = false)
	private String couleur;
	
	@Column(name = "date_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Column(name ="public", nullable = false)
	private Boolean isPublic;
	
	@Column(name = "image", nullable = false, unique = false)
	private String image;
	
	@ManyToOne
	// @JoinColumn (name = "deck_builder", nullable = false)
	private DeckBuilder deckBuilder;
	
	//@OneToOne
	//private Card commander;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	
    @JoinTable(
        name = "Deck_Cards", 
        joinColumns = { @JoinColumn(name = "card_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "deck_id") }
    )	
	private List<Card> cartes;
	
	
	
	public Deck() {
		cartes = new ArrayList<>();
	}



	
	
	

}
