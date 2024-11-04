package com.example.demo.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Color;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.DeckRepository;
import com.example.demo.services.CardService;
import com.example.demo.services.DeckService;
import com.example.demo.services.IDeckBuilderService;

@RestController
@RequestMapping("f_all")
public class AllController {
	
	// Contient les requetes nécessaires pour s'atuthentifier
	// Accessibles à tous
	
	@Autowired
	private IDeckBuilderService iDeckBuilderService;
	
	@Autowired
	private DeckService deckService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	DeckRepository deckRepository;
	
	@PostMapping("inscription")
	public DeckCreator inscription(@RequestBody DeckCreator db) {
		return iDeckBuilderService.inscription(db);
		
	}
	
	
	@PostMapping("connection")
	public String connection(@RequestBody Map<String, String> request) {
		return iDeckBuilderService.connection(request);
	}
	
	@GetMapping("getDecks")
	List<Deck> getDecksByFilter( @RequestParam(required = false) String name, @RequestParam(required = false) List<Format> formats,
			@RequestParam(required = false) List<Color> colors) {
		return deckService.getDecksByFilter(name, formats, colors);
	}
	
	@GetMapping("getCards")
	public List<Card> getCardsByFilter (@RequestParam(required = false) String name, @RequestParam(required = false) Long manaCost, 
			@RequestParam(required = false) Float value, @RequestParam(required = false) Format format,
			@RequestParam(required = false)Color color, @RequestParam(required = false) CardType type,
			@RequestParam(required = false)Rarity rarity, @RequestParam(required = false) Edition edition) {
		return cardService.getCardsByFilter(name, manaCost, value, format, color, type, rarity, edition);
	}
	
	@GetMapping("getDeck")
	Optional<Deck> getDeck (@RequestParam Long deckId) {
		return deckRepository.findById(deckId);
	}

}
