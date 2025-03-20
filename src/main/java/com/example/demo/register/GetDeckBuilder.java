package com.example.demo.register;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Card;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.UserActivity;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDeckBuilder {
		
		private Long id;

		private String pseudo;
		
		private String email;
		
		private String password;
		
		private LocalDate dateSign;
		
		private String avatar;
		
		private String bio;
		
		private UserActivity activity;



		



}
