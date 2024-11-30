package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Color;
import com.example.demo.repositories.ColorRepository;

@Service
public class ColorService {
	
	@Autowired
	private ColorRepository colorsRepository;
	
	public Color addColor(Color color) {
		return colorsRepository.save(color);
	}
	
	public String deleteColor(Long colorId) {
		colorsRepository.deleteById(colorId);
		return " couleur supprimée";
	}

}
