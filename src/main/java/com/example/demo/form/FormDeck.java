package com.example.demo.form;

import java.util.List;

import com.example.demo.entities.Color;
import com.example.demo.entities.Deck;
import com.example.demo.enums.EnumFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDeck {
	
	// private Deck deck;
	
	private String name;
	
	private String image;
	
	private EnumFormat format;
	
	private boolean bleu;
	private boolean blanc;
	private boolean vert;
	private boolean rouge;
	private boolean noir;
	private boolean incolore;

	
	// private List<Color> colors;
	
}
