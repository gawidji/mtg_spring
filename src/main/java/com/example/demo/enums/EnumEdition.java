package com.example.demo.enums;

public enum EnumEdition {
	
	
	LES_FRICHES_D_ELDRAINE ("Les Friches d'Eldaines"),
	LES_CAVERNES_OUBLIÉES_D_IXALAN ("Les Cavernes Oubliées d'Ixalan"),
	RAVNICA_REMASTERED ("Ravinca Remastered"),
	MURDERS_AT_KARLOV_MANOR ("Meutres au manoir Karlov"), 
	MODERN_HORIZONS_3 ("Modern Horizons 3"),
	BLOOMBURROW ("Bloomburrow");
	
	private String value;

	private EnumEdition(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
	
}
