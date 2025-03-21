package com.example.demo.entities;


import java.io.Serializable;
import java.util.List;

import com.example.demo.enums.EnumFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "format")
@Builder
public class Format implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom", unique=true)
	@Enumerated(EnumType.STRING)
	private EnumFormat name;
	
	@Column(name = "texte")
	private String text;
	
	@ManyToMany(mappedBy = "formats", fetch = FetchType.LAZY)
	private List<Card> cards;

}
