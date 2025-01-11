package com.example.demo.enums;

public enum CardType {
	
	TERRAIN ("Terrain"),
	CREATURE ("Créature"),
	PLANESWALKER ("Planeswalker"),
	ENCHANTEMENT ("Enchantement"),
	BATAILLE ("Bataille"),
	RITUEL ("Rituel"),
	EPHEMERE ("Ephemère"),
	ARTEFACT ("Artefact");
	
	private String value;

	private CardType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
