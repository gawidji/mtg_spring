package com.example.demo.register;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Card;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDeck {
		
		private Long id;

		private String name;
		
		private LocalDate dateCreation;
		
		private String image;
		
		private EnumFormat format;
		
		private List<EnumColor> colors = new ArrayList<>();
		
		private Float manaCost;
		
		private Float value;
		
		private Boolean isPublic;

	    private DeckCreator deckBuilder;
	    
	    private String deckBuilderName;

		private List<Card> cartes;
		
		private Card commander;




}
