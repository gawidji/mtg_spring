package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Color;
import com.example.demo.entities.Format;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import com.example.demo.enums.UserActivity;
import com.example.demo.repositories.ColorRepository;
import com.example.demo.repositories.FormatRepository;

@Service
public class EnumService {
	
	@Autowired
	private ColorRepository colorsRepository;
	
	@Autowired
	private FormatRepository formatsRepository;	
	
	public List<EnumColor> getColorsNames() {
		List<Color> colors = colorsRepository.findAll();
		List <EnumColor> colorsNames = new ArrayList<>();
		for (Color color : colors) {
			EnumColor enumColor = color.getName();
			colorsNames.add(enumColor);
		}
		return colorsNames;
	}
	
	public List<EnumFormat> getFormatsNames() {
		List<Format> formats = formatsRepository.findAll();
		List <EnumFormat> formatsNames = new ArrayList<>();
		for (Format format : formats) {
			EnumFormat enumFormat = format.getName();
			formatsNames.add(enumFormat);
		}
		return formatsNames;
	}
	
	public List<EnumRarity> getRarities() {
		EnumRarity[] raritiesTab = EnumRarity.values();
		List<EnumRarity> rarities = new ArrayList<>();
		for (EnumRarity enumRarity : raritiesTab) {
			rarities.add(enumRarity);
		}
		return rarities;
	}
	
	public List<EnumEdition> getEditions() {
		EnumEdition[] editionsTab = EnumEdition.values();
		List<EnumEdition> editions = new ArrayList<>();
		for (EnumEdition enumEdition : editionsTab) {
			editions.add(enumEdition);
		}
		return editions;
	}
	
	public List<CardType> getTypes() {
		CardType[] typesTab = CardType.values();
		List<CardType> types = new ArrayList<>();
		for (CardType enumType : typesTab) {
			types.add(enumType);
		}
		return types;
	}
	
	public List<UserActivity> getActivities() {
		UserActivity[] activitiesTab = UserActivity.values();
		List<UserActivity> activities = new ArrayList<>();
		for (UserActivity enumActivity : activitiesTab) {
			activities.add(enumActivity);
		}
		return activities;
	}

	
	

}
