package com.example.demo.form;

import java.util.List;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Format;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormCard {
	
	private String name;
	
	private String text;
	
	private String image;
	
	private Long manaCost;
	
	private Float value;
		
	private List<EnumColor> colors;
	
	private List<EnumFormat> formats;
	
	private CardType type;
	
	private String legendary;
	
	private EnumRarity rarity;
	
	private EnumEdition edition;
}
