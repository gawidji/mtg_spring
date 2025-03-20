package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.entities.Color;
import com.example.demo.entities.DeckCreator;
import com.example.demo.entities.Format;
import com.example.demo.enums.UserActivity;
import com.example.demo.form.FormCard;
import com.example.demo.register.GetDeckBuilder;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.services.ColorService;
import com.example.demo.services.DeckBuilderService;
import com.example.demo.services.FormatService;
import com.example.demo.services.IAuthenticationService;
import com.example.demo.services.ICardService;
import com.example.demo.services.IDeckBuilderService;
import com.example.demo.services.IDeckService;

@RestController
@RequestMapping("f_admin")
public class AdminController {
	
	// Les requetes seulement effectuées par l'admin
	
	@Autowired
	private IDeckBuilderService iAccountService;
	
	@Autowired
	private IDeckService iDeckService;
	
	@Autowired
	private IAuthenticationService iAuthenticationService;
	
	@Autowired
	ICardService iCardService;
	
	@Autowired
	ColorService colorService;
	
	@Autowired
	FormatService formatService;
	
	@Autowired
	DeckBuilderRepository deckBuilderRepository;
	
	
	
	@PostMapping("addAdmin")
	public DeckCreator addAdmin(Authentication authentication, @RequestBody DeckCreator db) {
		return iAuthenticationService.addAdmin(db);
		
	}
	
	@DeleteMapping("deleteAccount")
	public String deleteAccount(Authentication authentication, @RequestParam Long dbID) {
		return iAccountService.deleteDeckBuilder(dbID);
	}
	
	@GetMapping("getUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
	public List <GetDeckBuilder> getDeckBuildersByFilter ( Authentication authentication, @RequestParam(required=false) String pseudo, @RequestParam(required=false) String email,
	@RequestParam(required=false) List<UserActivity> activities) {
		return iAccountService.getDeckBuildersByFilter(pseudo, email, activities);
	}
	
	@GetMapping("getUsersTest")
	public List <GetDeckBuilder> getDeckBuildersByFilters ( @RequestParam(required=false) String pseudo, @RequestParam(required=false) String email,
	@RequestParam(required=false) List<UserActivity> activities) {
		return iAccountService.getDeckBuildersByFilter(pseudo, email, activities);
	}
	
	@GetMapping("getUser")
	public ResponseEntity getDeckBuilder(@RequestParam Long userID) {
		
		Optional <DeckCreator> user = deckBuilderRepository.findById(userID);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(iAccountService.getDeckBuilder(user.get()));
		}
		
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User non trouvé");

	}
	
	@GetMapping("getDecksOfUser")
	public ResponseEntity getDecksByUser(@RequestParam Long userID){
		
		Optional <DeckCreator> user = deckBuilderRepository.findById(userID);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(iDeckService.getDecksByUser(user.get()));
		}
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User non trouvé");
	}
	
	
	@PostMapping("addCardTest")
	public ResponseEntity addCard(@RequestBody FormCard cardRegister ) {
		
		 return ResponseEntity.ok(iCardService.addCardTest(cardRegister) );
	}
	
	/*
	@PostMapping("addCard")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity addCard(Authentication authentication, @RequestBody FormCard cardRegister ) {
		
		 iCardService.addCard(cardRegister.getCard(), cardRegister.getColors(), cardRegister.getFormats());
		 return ResponseEntity.ok("Carte ajoutée : " + cardRegister.getCard() );
	}
	*/
	
	@PutMapping("updateCard")
    @PreAuthorize("hasAuthority('ADMIN')")
	public Card updateCard (Authentication authentication, Long cardID, Card cardUpdate) {
		
		return iCardService.updateCard(cardID, cardUpdate);
	}
	
	@DeleteMapping("deleteCard")
    @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteCard(Authentication authentication, @RequestParam long cardID) {
		return iCardService.deleteCard(cardID);
	}
	
	
	@PostMapping("addColors")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity addColors(Authentication authentication, @RequestBody List <Color> colors ) {
		
		 colorService.addColor(colors);
		 return ResponseEntity.ok("Couleurs ajoutées");

	}
	
	@DeleteMapping("deleteColor")
    @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteColor(@RequestParam long colorId) {
		return colorService.deleteColor(colorId);
	}
	
	
	@PostMapping("addFormats")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity addFormat(Authentication authentication, @RequestBody List<Format> formats ) {
		
		formatService.addFormats(formats);
		return ResponseEntity.ok("Formats ajoutés");
	}
	
	@DeleteMapping("deleteFormat")
    @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteFormat(Authentication authentication, @RequestParam long formatId) {
		return formatService.deleteFormat(formatId);
	}
}
