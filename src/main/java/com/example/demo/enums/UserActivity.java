package com.example.demo.enums;

public enum UserActivity {
	
		VIEWVER ("Viewver"),
		// N'a créé aucun deck, consulte juste le site
		
		CREATOR ("Creator"),
		// A créé au moins un deck mais seulement en pv
		
		PUBLISHER ("Publisher");
		// A publié au moins un deck
		
		private String value;

		private UserActivity(String value) {
			this.value = value;
		}

		public String getValue() {
			return value; 
		}
		

}
