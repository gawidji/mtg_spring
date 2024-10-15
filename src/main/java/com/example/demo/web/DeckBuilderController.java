package com.example.demo.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.DeckBuilder;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.services.IDeckBuilderService;

@RestController
@RequestMapping("db")
public class DeckBuilderController {
	
	@Autowired
	private IDeckBuilderService iDeckBuilderService;
	
	
	@Autowired
	private DeckBuilderRepository deckBuilderRepository;
	
	@PostMapping("inscription")
	public DeckBuilder inscription(@RequestBody DeckBuilder db) {
		return iDeckBuilderService.inscription(db);
		
	}
	
	@PostMapping("addAdmin")
	public DeckBuilder addAdmin(@RequestBody DeckBuilder db) {
		return iDeckBuilderService.inscription(db);
		
	}
	
	
	@PostMapping("connection")
	public String connection(@RequestBody Map<String, String> request) {
		return iDeckBuilderService.connection(request);
	}
	
	
	@GetMapping("{id}")
	public String recupDbById(Long id) {
		Optional<DeckBuilder> db = deckBuilderRepository.findById(id);
		
		if(db.isPresent()) {
			if(db.get().getIsAdmin()) {
			return "admin";
			}
			return "notAdmin";
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}

}
