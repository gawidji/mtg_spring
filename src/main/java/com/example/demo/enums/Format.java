package com.example.demo.enums;

public enum Format {
	
	COMMANDER ("Commander"),
	STANDARD ("Standard"),
	MODERN ("Modern"),
	PIONEER ("Pioneer"),
	LEGACY ("Legacy"),
	PAUPER ("Pauper"),
	VINTAGE ("Vintage");
	
	
	private String value;

	private Format(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
