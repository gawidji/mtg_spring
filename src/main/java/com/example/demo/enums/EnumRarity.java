package com.example.demo.enums;

public enum EnumRarity {
	
	MYTHIQUE ("Mythique"),
	RARE ("Rare"),
	PEU_COMMUNE ("Peu commune"),
	COMMUNE ("Commune");
	
	private String value;

	private EnumRarity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

}
