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
import com.example.demo.entities.Color;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.UserActivity;
import com.example.demo.register.CardRegister;
import com.example.demo.services.ColorService;
import com.example.demo.services.IAuthenticationService;
import com.example.demo.services.ICardService;
import com.example.demo.services.IDeckBuilderService;

@RestController
@RequestMapping("f_admin")
public class AdminController {
	
	// Les requetes seulement effectuées par l'admin
	
	@Autowired
	private IDeckBuilderService iDeckBuilderService;
	
	@Autowired
	private IAuthenticationService iAuthenticationService;
	
	@Autowired
	ICardService iCardService;
	
	@Autowired
	ColorService colorService;
	
	@PostMapping("addAdmin")
	public DeckCreator addAdmin(@RequestBody DeckCreator db) {
		return iAuthenticationService.addAdmin(db);
		
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
	public Card addCard(@RequestBody CardRegister cardRegister ) {
		
		return iCardService.addCard(cardRegister.getCard(), cardRegister.getColors());
	}
	
	@PutMapping("updateCard")
	public Card updateCard (Long cardID, Card cardUpdate) {
		
		return iCardService.updateCard(cardID, cardUpdate);
	}
	
	@DeleteMapping("deleteCard")
	public String deleteCard(@RequestParam long cardID) {
		return iCardService.deleteCard(cardID);
	}
	
	
	@PostMapping("addColor")
	public Color addColor(@RequestBody Color color ) {
		
		return colorService.addColor(color);
	}
	
	@DeleteMapping("deleteColor")
	public String deleteColor(@RequestParam long colorId) {
		return colorService.deleteColor(colorId);
	}
}
