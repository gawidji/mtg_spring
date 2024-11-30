package com.example.demo.enums;

public enum EnumColor {
	
	BLEU ("Bleu"),
	VERT ("Vert"), 
	BLANC ("Blanc"), 
	ROUGE ("Rouge"), 
	NOIR ("Noir"), 
	INCOLORE ("Incolore");
	
	private String value;

	private EnumColor(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
