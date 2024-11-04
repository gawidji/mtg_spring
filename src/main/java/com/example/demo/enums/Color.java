package com.example.demo.enums;

public enum Color {
	
	BLEU ("Bleu"),
	VERT ("Vert"), 
	BLANC ("Blanc"), 
	ROUGE ("Rouge"), 
	NOIR ("Noir"), 
	INCOLORE ("Incolore");
	
	private String value;

	private Color(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
