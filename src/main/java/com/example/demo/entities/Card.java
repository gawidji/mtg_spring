package com.example.demo.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumRarity;
import com.example.demo.enums.CardType;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
@ToString(exclude = {"formats", "colors", "decks", "decksCommander"})


public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom", nullable = false, unique = true)
	private String name;
	
	@Column(name = "texte", nullable = false, unique = false,  length = 5000 )
	private String text;
	
	@Column(name = "image", unique = false)
	private String image;
	
	@Column(name="cout_mana")
	private Long manaCost;
	
	@Column(name = "valeur", unique = false)
	private Float value;
	
	@ManyToMany
    @JoinTable(
        name = "Cards_Formats", 
        joinColumns = { @JoinColumn(name = "card_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "format_id") }
    )	
	private List<Format> formats = new ArrayList<>();
	
	
	@ManyToMany
    @JoinTable(
        name = "Cards_Colors", 
        joinColumns = { @JoinColumn(name = "card_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "color_id") }
     )	
	private List<Color> colors = new ArrayList<>();
	
	
	@Enumerated(EnumType.STRING)
	private CardType type;
	
	@Column(name = "legendaire", nullable = true)
	private String legendary;
	
	@Column(name = "rareté", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private EnumRarity rarity;
	
	@Enumerated(EnumType.STRING)
	private EnumEdition edition;
	
	@ManyToMany(mappedBy = "cardsLiked", fetch = FetchType.LAZY)
	private Set<DeckCreator> deckBuilders;
	
	
	@Column(name = "nb_likes")
	private Long likeNumber;

	
	@ManyToMany(mappedBy = "cards")
	private Set<Deck> decks;
	
	@Column(name = "nb_decks")
	private Long deckNumber;
	
	@OneToMany(mappedBy = "commander", cascade = CascadeType.ALL)
	private Set<Deck> decksCommander;
	
	@Column(name = "nb_commander")
	private Long commanderNumber;
	
	
}
