package com.example.demo.enums;

public enum Rarity {
	
	MYTHIQUE ("Mythique"),
	RARE ("Rare"),
	PEU_COMMUNE ("Peu commune"),
	COMMUNE ("Commune");
	
	private String value;

	private Rarity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

}
