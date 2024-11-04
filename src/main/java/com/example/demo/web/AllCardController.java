package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.enums.Format;
import com.example.demo.services.ICardService;

@RestController
@RequestMapping("f_all")
public class AllCardController {
	
	// Ici les méthodes pour rechercher des cartes accessibles par tous
	
	@Autowired
	ICardService iCardService;
	
	@GetMapping("CardFormats")
	public List<Format> getCardFormats(@RequestParam Long cardID) {
		return iCardService.getCardFormats(cardID);
	}
	
	// Méthode pour filtrer les cartes par couleurs, editions, formats, couts en mana
}
