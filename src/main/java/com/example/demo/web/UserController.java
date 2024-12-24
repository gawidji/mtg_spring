package com.example.demo.web;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumRarity;
import com.example.demo.register.FormDeck;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.services.IAccountService;
import com.example.demo.services.IDeckService;

@RestController
@RequestMapping("f_user")
public class UserController {
	
	// Ici les méthodes déstiné à l'user connecté
	// créer, modifier, rechercher des decks accessibles par les users auth
	
	@Autowired
	private IAccountService iDeckBuilderService;
	@Autowired
	private IDeckService iDeckService;
	@Autowired
	private DeckBuilderRepository deckBuilderRepository;
	
	
	@PutMapping("updateAccount")
	public DeckCreator updateAccount(@RequestParam String email, @RequestBody DeckCreator deckBuilder) {
		return iDeckBuilderService.updateAccount(email, deckBuilder);
	}
	// Nécessite une atuh par token pour que email soit celui de l'user connecté
	
	@DeleteMapping("deleteAccount")
	public String deleteAccount(@RequestParam Long dbID) {
		return iDeckBuilderService.deleteDeckBuilder(dbID);
	}
	// Appel de deleteDeckBuilder mais doit nécessité une auth par token pour que le dbID soit celui de l'user connecté
	
	
	@PostMapping("addDeck")
	public ResponseEntity addDeck( Authentication authentication, @RequestBody FormDeck deckRegister) {
		
		Optional <DeckCreator> user = deckBuilderRepository.findByEmail(authentication.getName());
		
		if(user.isPresent()) {
			iDeckService.addDeckWithForm(user.get(), deckRegister);
			return ResponseEntity.ok("Nouveau deck créé : " + deckRegister.getName() );
		}
		
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec de l'authentification");

	}
	
	@DeleteMapping("deleteDeck")
	public String deleteDeck(@RequestParam Long deckID) {
		return iDeckService.deleteDeck(deckID);
	}
	
	@PutMapping("updateDeck")
	public Deck updateDeck(@RequestParam Long deckID, @RequestBody Deck deckUpdate) {
		return iDeckService.updateDeck(deckID, deckUpdate);
	}

	@GetMapping("getCards")
	public List<Card> getCardsByFilterForDeck (@RequestParam Long deckId, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long manaCostMin, @RequestParam(required = false) Long manaCostMax,
			@RequestParam(required = false) Float valueMin, @RequestParam(required = false) Float valueMax,
			@RequestParam(required = false) List <CardType> types, 
			@RequestParam(required = false) List <EnumRarity> rarities, @RequestParam(required = false) List<EnumEdition> editions) {
		return iDeckService.getCardsByFilterForDeck(deckId, name, manaCostMin, manaCostMax, valueMin, valueMax, types, rarities, editions);
	}
	
	@GetMapping("getCommander")
	public List<Card> getCommanderByFilterForDeck(@RequestParam Long deckId, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long manaCostMin, @RequestParam(required = false) Long manaCostMax,
			@RequestParam(required = false) Float valueMin, @RequestParam(required = false) Float valueMax, 
			@RequestParam(required = false) List <EnumRarity> rarities,@RequestParam(required = false) List<EnumEdition> editions) {
			return iDeckService.getCommanderByFilterForDeck(deckId, name, manaCostMin, manaCostMax,  valueMin, valueMax, rarities, editions);
	}

	
	
	@PostMapping("addCardOnDeck")
	public Deck addCardOnDeck(@RequestParam Long cardId, @RequestParam Long deckId) {
		return iDeckService.addCardOnDeck(cardId, deckId);
	}
	
	@PostMapping("addCommanderOnDeck")
	public Deck addCommanderOnDeck(Long cardId, Long deckId) {
		return iDeckService.addCommanderOnDeck(cardId, deckId);
	}
	
	@DeleteMapping("deleteCardOnDeck")
	public String deleteCardOnDeck(@RequestParam Long cardId, @RequestParam Long deckId) {
		return iDeckService.deleteCardOnDeck(cardId, deckId);
	}
	
	@PutMapping("deleteCommanderOnDeck")
	public String deleteCommanderOnDeck(Long deckID) {
		return iDeckService.deleteCommanderOnDeck(deckID);
	}
	
	@PutMapping("deckPublic")
	public String publishDeck(@RequestParam Long deckID) {
		return iDeckService.publishDeck(deckID);
	}
	
	@PutMapping("deckPrivate")
	public String privateDeck(Long deckID) {
		return iDeckService.privateDeck(deckID);
	}
	
	
	@GetMapping("getDeckByUser")
	public Set<Deck> getDeckByUser(Long dbID){
		return iDeckService.getDeckByUser(dbID);
	}

	
	@GetMapping("deckValue")
	public float getDeckValue(@RequestParam Long deckID) {
		return iDeckService.getDeckValue(deckID);
	}
	
	@GetMapping("deckCost")
	public float getDeckManaCost(@RequestParam Long deckID) {
		return iDeckService.getDeckManaCost(deckID);
	}
	
	
	
	// Toutes les méthodes sont la 


}
