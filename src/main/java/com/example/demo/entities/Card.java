package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Color;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")

public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom", nullable = false, unique = true)
	private String name;
	
	@Column(name = "texte", nullable = false, unique = false)
	private String text;
	
	@Column(name="cout_mana")
	private Long manaCost;
	
	@Column(name = "valeur (€)", unique = false)
	private Float value;
	
	@Lob
	@Column(name = "formats autorisés", nullable= false, unique = false)
	@Enumerated(EnumType.STRING)
	private List<Format> formats;
	
	@Lob
	@Column(name = "couleur", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private List<Color> colors;
	
	
	@Enumerated(EnumType.STRING)
	private CardType type;
	
	@Column(name = "rareté", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private Rarity rarity;
	
	@Enumerated(EnumType.STRING)
	private Edition edition;
	
	
	@Column(name = "image", unique = false)
	private String image;
	
	@ManyToMany(mappedBy = "cartes")
	private List<Deck> decks;
	
	@OneToMany(mappedBy = "commander", cascade = CascadeType.ALL)
	private Set<Deck> decksCommander;

}
