package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;
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
	
	@DeleteMapping("deleteAccount")
	public String deleteAccount(@RequestParam Long dbID) {
		return iDeckBuilderService.deleteDeckBuilder(dbID);
	}
	
	@GetMapping("getUsers")
	public List <DeckCreator> getDeckBuildersByFilter ( @RequestParam(required=false) String pseudo, @RequestParam(required=false) String email,
	@RequestParam(required=false) List<UserActivity> activities) {
		return iDeckBuilderService.getDeckBuildersByFilter(pseudo, email, activities);
	}
	
	@GetMapping("getAdmins")
	public List<DeckCreator> getAdmins(@RequestParam(required=false) String pseudo, @RequestParam(required=false) String email) {
		return iDeckBuilderService.getAdmins(pseudo, email);
	}
	
	@PostMapping("addCard")
	public Card addCard(@RequestBody Card card) {
		
		return iCardService.addCard(card);
	}
	
	@PutMapping("updateCard")
	public Card updateCard (Long cardID, Card cardUpdate) {
		
		return iCardService.updateCard(cardID, cardUpdate);
	}
	
	@DeleteMapping("deleteCard")
	public String deleteCard(@RequestParam long cardID) {
		return iCardService.deleteCard(cardID);
	}
	
	// Ajouter une méthode pour bannir un user 

}
