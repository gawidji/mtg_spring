package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.IDeckBuilderService;

@RestController
@RequestMapping("f_admin")
public class DeckBuilderAdminController {
	
	// Les requetes seulement effectuées par l'admin
	
	@Autowired
	private IDeckBuilderService iDeckBuilderService;
	
	
	@GetMapping("user/{id}")
	public Boolean isUserAdmin(@PathVariable Long id) {
		
		return iDeckBuilderService.isUserAdmin(id);

	}

}
