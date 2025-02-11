package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

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
public class FormCardFilter {
	
	
	private String name;
			
	private Long manaCostMin;
	
	private Long manaCostMax;

	private Float valueMin;

	private Float valueMax;
	
	private List<EnumFormat> formats;
	
	private List<EnumColor> colors;
	
	private List<CardType> types;
	
	private List<EnumRarity> rarities;
	
	private List<EnumEdition> editions;
	

}
