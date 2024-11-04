package com.example.demo.entities;

import java.util.Date;
import java.util.List;

import com.example.demo.enums.Color;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	private Date dateCreation;
	
	@Column(name = "image")
	private String image;
	
	@Enumerated(EnumType.STRING)
	private Format format;
	
	@Lob
	@Column(name = "couleur", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private List<Color> colors;
	
	@Column(name="cout_mana")
	private Float manaCost;
	
	@Column(name="valeur (€)")
	private Float value;
	
	@Column(name ="public", nullable = false)
	private Boolean isPublic;
	
	@ManyToOne
	@JoinColumn(name = "deck_builder_id", nullable = false)
    private DeckCreator deckBuilder;
	

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Deck_Cards", 
        joinColumns = { @JoinColumn(name = "deck_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "card_id") }
    )	
	private List<Card> cartes;
	

	@ManyToOne
	@JoinColumn (name= "commander_id", nullable = true)
	private Card commander;


	
	
	

}
