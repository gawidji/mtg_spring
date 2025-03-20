package com.example.demo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.enums.EnumFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deck")
@Builder
@Entity
public class Deck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "nom", length = 25, nullable = false, unique = false)
	private String name;
	
	@Column(name = "date_création")
	@Temporal(TemporalType.DATE)
	private LocalDate dateCreation;
	
	@Column(name = "image")
	private String image;
	
	@Enumerated(EnumType.STRING)
	private EnumFormat format;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Decks_Colors", 
        joinColumns = { @JoinColumn(name = "deck_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "color_id") }
    )
	private List<Color> colors = new ArrayList<>();
	
	@Column(name="cout_mana")
	private Float manaCost;
	
	@Column(name="valeur (€)")
	private Float value;
	
	@Column(name ="public", nullable = false)
	private Boolean isPublic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deck_builder_id", nullable = true)
    private DeckCreator deckBuilder;
	 
	
	@ManyToMany(mappedBy = "decksLiked", fetch = FetchType.LAZY)
	private Set<DeckCreator> deckBuilders = new HashSet<>();
	
	@Column(name = "likeNumber")
	private Long likeNumber; 
	

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Deck_Cards", 
        joinColumns = { @JoinColumn(name = "deck_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "card_id") }
    )	
	private List<Card> cards;
	

	@ManyToOne
	@JoinColumn (name= "commander_id", nullable = true)
	private Card commander;
	
	


	
	
	

}
