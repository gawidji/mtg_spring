package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Color;
import com.example.demo.enums.EnumColor;
import com.example.demo.repositories.ColorRepository;

@Service
public class ColorService {
	
	@Autowired
	private ColorRepository colorsRepository;
	
	public void addColor(List<Color> colors) {
		for (Color color : colors) {
			colorsRepository.save(color);
		}
	}
	
	public String deleteColor(Long colorId) {
		colorsRepository.deleteById(colorId);
		return " couleur supprimée";
	}
	
	

}
