package com.example.demo.register;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.entities.Deck;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCard {
	
	private Long id;
	
	private String name;
	
	private String text;
	
	private String image;
	
	private Long manaCost;
	
	private Float value;
	
	private List<EnumFormat> formats = new ArrayList<>();
	
	private List<EnumColor> colors = new ArrayList<>();
	
	private CardType type;
	
	private EnumRarity rarity;
	
	private EnumEdition edition;
	
	private List<Deck> decks;
	
	private Set<Deck> decksCommander;

}
