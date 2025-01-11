package com.example.demo.register;

import java.util.List;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormCard {
	
	private Card card;
		
	private List<Color> colors;
	
	private List<Format> formats;

}
