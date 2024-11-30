package com.example.demo.enums;

public enum EnumFormat {
	
	COMMANDER ("Commander"),
	STANDARD ("Standard"),
	MODERN ("Modern"),
	PIONEER ("Pioneer"),
	LEGACY ("Legacy"),
	PAUPER ("Pauper"),
	VINTAGE ("Vintage");
	
	
	private String value;

	private EnumFormat(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
