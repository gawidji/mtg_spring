package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.services.ICardService;

@RestController
@RequestMapping("f_all")
public class CardController {
	
	// Ici les méthodes pour rechercher des cartes accessibles par tous
	
	@Autowired
	ICardService iCardService;
	
	
	public Card addCard(@RequestBody Card card) {
		
		return iCardService.addCard(card);
	}
}
