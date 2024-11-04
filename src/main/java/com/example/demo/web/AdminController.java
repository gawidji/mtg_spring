package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.entities.DeckCreator;
import com.example.demo.services.ICardService;
import com.example.demo.services.IDeckBuilderService;

@RestController
@RequestMapping("f_admin")
public class AdminController {
	
	// Les requetes seulement effectuées par l'admin
	
	@Autowired
	private IDeckBuilderService iDeckBuilderService;
	
	@Autowired
	ICardService iCardService;
	
	@PostMapping("addAdmin")
	public DeckCreator addAdmin(@RequestBody DeckCreator db) {
		return iDeckBuilderService.addAdmin(db);
		
	}
	
	
	@GetMapping("userAdmin")
	public Boolean isUserAdmin(@RequestParam Long id) {
		
		return iDeckBuilderService.isUserAdmin(id);

	}
	
	@PostMapping("addCard")
	public Card addCard(@RequestBody Card card) {
		
		return iCardService.addCard(card);
	}
	
	@DeleteMapping("deleteCard")
	public String deleteCard(@RequestParam long cardID) {
		return iCardService.deleteCard(cardID);
	}
	
	// Ajouter une méthode pour bannir un user 

}
