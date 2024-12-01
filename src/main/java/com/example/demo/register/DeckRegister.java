package com.example.demo.register;

import java.util.List;

import com.example.demo.entities.Color;
import com.example.demo.entities.Deck;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeckRegister {
	
	private Deck deck;
	
	private List<Color> colors;
	
}
