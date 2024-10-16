package com.example.demo.web;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.DeckBuilder;
import com.example.demo.services.IDeckBuilderService;

@RestController
@RequestMapping("db")
public class DeckBuilderController {
	
	@Autowired
	private IDeckBuilderService iDeckBuilderService;
	
	
	
	@PostMapping("inscription")
	public DeckBuilder inscription(@RequestBody DeckBuilder db) {
		return iDeckBuilderService.inscription(db);
		
	}
	
	@PostMapping("addAdmin")
	public DeckBuilder addAdmin(@RequestBody DeckBuilder db) {
		return iDeckBuilderService.addAdmin(db);
		
	}
	
	
	@PostMapping("connection")
	public String connection(@RequestBody Map<String, String> request) {
		return iDeckBuilderService.connection(request);
	}
	
	
	@GetMapping("{id}")
	public Boolean isUserAdmin(@PathVariable Long id) {
		
		return iDeckBuilderService.isUserAdmin(id);

	}

}
