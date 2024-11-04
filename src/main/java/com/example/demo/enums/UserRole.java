package com.example.demo.enums;

public enum UserRole {
	USER ("User"),
	ADMIN ("Admin");
	
	private String value;

	private UserRole(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
