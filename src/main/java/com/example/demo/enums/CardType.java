package com.example.demo.enums;

public enum CardType {
	
	TERRAIN ("Terrain"),
	CREATURE ("Créature"),
	CREATURE_LEGENDAIRE ("Créature légendaire"),
	PLANESWALKER ("Planeswalker"),
	ENCHANTEMENT ("Enchantement"),
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
