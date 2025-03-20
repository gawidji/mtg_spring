package com.example.demo.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Format;
import com.example.demo.entities.Deck;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import com.example.demo.form.FormCard;
import com.example.demo.register.GetCard;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.ColorRepository;
import com.example.demo.repositories.DeckRepository;
import com.example.demo.repositories.FormatRepository;

@Service
public class CardService implements ICardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private FormatRepository formatRepository;
	
	// homePage
	
	@Override
	public List<GetCard> getTopCards() {
		
		List<Card> cards = cardRepository.findAll();
		List<Card> topCards = new ArrayList<>();
		
		for (Card card : cards) {
			if(card.getLikeNumber() != null) {
				topCards.add(card);
			}
			
		}
		
		
		topCards.sort(Comparator.comparingLong(Card::getLikeNumber).reversed());
		List<GetCard> topGetCards = new ArrayList<>();
		
		for (Card card : topCards) {
			GetCard testCard = new GetCard();
			testCard.setId(card.getId());
			testCard.setName(card.getName());
			testCard.setImage(card.getImage());
			testCard.setText(card.getText());
			testCard.setManaCost(card.getManaCost());
			testCard.setValue(card.getValue());
			testCard.setType(card.getType());
			testCard.setRarity(card.getRarity());
			testCard.setEdition(card.getEdition());
			testCard.setLikeNumber(card.getLikeNumber());
			
			for (Color color : card.getColors()) {
				testCard.getColors().add(color.getName());
			}	
			for (Format format : card.getFormats()) {
				testCard.getFormats().add(format.getName());
			}	
			topGetCards.add(testCard);
		}
		
		return topGetCards;
	}
	
	@Override
	public List<GetCard> getTop3Cards() {
		
		List<Card> cards = cardRepository.findAll();
		List<Card> topCards = new ArrayList<>();
		
		for (Card card : cards) {
			if(card.getLikeNumber() != null) {
				topCards.add(card);
			}
			
		}
		topCards.sort(Comparator.comparingLong(Card::getLikeNumber).reversed());
				
		List<GetCard> topGetCards = new ArrayList<>();
		
		for (Card card : topCards) {
			GetCard testCard = new GetCard();
			testCard.setId(card.getId());
			testCard.setName(card.getName());
			testCard.setImage(card.getImage());
			testCard.setText(card.getText());
			testCard.setManaCost(card.getManaCost());
			testCard.setValue(card.getValue());
			testCard.setType(card.getType());
			testCard.setRarity(card.getRarity());
			testCard.setEdition(card.getEdition());
			testCard.setLikeNumber(card.getLikeNumber());
			
			for (Color color : card.getColors()) {
				testCard.getColors().add(color.getName());
			}	
			for (Format format : card.getFormats()) {
				testCard.getFormats().add(format.getName());
			}	
			topGetCards.add(testCard);
			if(topGetCards.size() == 3) {
				break;
			}
		}
		
		return topGetCards;
	}
	
	
	
	// cardsPage
	
	@Override
	public List<GetCard> getCardsByFilter (String name,  Long manaCostMin, Long manaCostMax, 
			Float valueMin,	Float valueMax, List<EnumFormat> formats, List<EnumColor> colors, 
			List<CardType> types, String legendary, List<EnumRarity> rarities, List<EnumEdition> editions) {
		
		List<Color> colorsEntities = new ArrayList<>();	
		if(colors != null) {
			for (EnumColor color : colors) {	
				colorsEntities.add(colorRepository.findByName(color));
			}
		}
		else {
			colorsEntities = null;
		}
		
		
		List<Format> formatsEntities = new ArrayList<>();	
		if(formats != null) {
			for (EnumFormat format : formats) {
				formatsEntities.add(formatRepository.findByName(format));
			}
		}
		else {
			formatsEntities = null;
		}
		
		List<Card> cards = cardRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax, types,
				legendary, rarities, editions, colorsEntities, formatsEntities);
		
		List<GetCard> cardsReturn = new ArrayList<>();
		
		for (Card card : cards) {
			GetCard testCard = new GetCard();
			testCard.setId(card.getId());
			testCard.setName(card.getName());
			testCard.setImage(card.getImage());
			testCard.setText(card.getText());
			testCard.setType(card.getType());
			
			for (Color color : card.getColors()) {
				testCard.getColors().add(color.getName());
			}	
			for (Format format : card.getFormats()) {
				testCard.getFormats().add(format.getName());
			}	
			cardsReturn.add(testCard);
		}
		
		return cardsReturn;

	}
	
	
	// CardSelected
	
	@Override
	public GetCard getCardById(Long cardId) {
		 
		Card cardFind = cardRepository.findById(cardId).get();
		 if (cardFind != null) {
			 GetCard cardReturn = new GetCard();
				
			 cardReturn.setId(cardFind.getId());
			 cardReturn.setName(cardFind.getName());
			 cardReturn.setText(cardFind.getText());
			 cardReturn.setImage(cardFind.getImage());
			 cardReturn.setManaCost(cardFind.getManaCost());
			 cardReturn.setValue(cardFind.getValue());
			 cardReturn.setRarity(cardFind.getRarity());
			 cardReturn.setType(cardFind.getType());
			 
			 List <EnumColor> cardTestColors = new ArrayList<>();
			 for (Color color : cardFind.getColors()) {
				 cardTestColors.add(color.getName());
			}
			 cardReturn.setColors(cardTestColors);
			 
			 List <EnumFormat> cardTestFormats = new ArrayList<>();
			 for (Format format : cardFind.getFormats()) {
				 cardTestFormats.add(format.getName());
			}
			 cardReturn.setFormats(cardTestFormats);
			 
			 return cardReturn;
		 }
		 	return null;


	}
	
	
	@Override
	public List<Card> findByColors(List<EnumColor> colors) {
		
		List<Color> colorsFind = new ArrayList<>();	 

		for (EnumColor color : colors) {
			
			colorsFind.add(colorRepository.findByName(color));
		}
		
		return cardRepository.findByColorsIn(colorsFind);
	}
	
	
	
	@Override
	public List<Card> findByFormats(List<EnumFormat> formats) {
		
		List<Format> formatsFind = new ArrayList<>();	 

		for (EnumFormat format : formats) {
			
			formatsFind.add(formatRepository.findByName(format));
		}
		
		return cardRepository.findByFormatsIn(formatsFind);
	}
	
	
	
	@Override
	public List<GetCard> getCardsForDeck (Long DeckID, String name,  Long manaCostMin, Long manaCostMax, 
			Float valueMin,	Float valueMax, List<EnumColor> colorFilter, 
			List<CardType> types, String legendary, List<EnumRarity> rarities, List<EnumEdition> editions) {
		
		// Retrouve le deck 
		Optional<Deck> deck = deckRepository.findById(DeckID);
		
		// Récupère les couleurs et le format du deck
		List<Color> deckColorsEntities = new ArrayList<>();
		List<Format> deckFormatEntities = new ArrayList<>();
		if(deck.isPresent()) {
		for (Color color : deck.get().getColors()) {	
				deckColorsEntities.add(color);
			}
		EnumFormat deckFormat = deck.get().getFormat();
		Format deckFormatEntity = formatRepository.findByName(deckFormat);
	    deckFormatEntities.add(deckFormatEntity); 
		}
			
		
		// Convertit les EnumColor du filtre color (le filtre peut s'ajouter au premier filtre effectué avec les couleurs du deck)
		List<Color> colorsEntities = new ArrayList<>();	
		if(colorFilter != null) {
			for (EnumColor color : colorFilter) {	
				colorsEntities.add(colorRepository.findByName(color));
			}
		}
		else {
			colorsEntities = null;
		}
		
			
		
		List<Card> cards = cardRepository.findForDeck(name, manaCostMin, manaCostMax, valueMin, valueMax, types,
				legendary, rarities, editions, colorsEntities, deckColorsEntities, deckFormatEntities);
		
		List<GetCard> cardsReturn = new ArrayList<>();
		
		for (Card card : cards) {
			GetCard testCard = new GetCard();
			testCard.setId(card.getId());
			testCard.setName(card.getName());
			testCard.setImage(card.getImage());
			testCard.setText(card.getText());
			testCard.setType(card.getType());
			
			for (Color color : card.getColors()) {
				testCard.getColors().add(color.getName());
			}	
			for (Format format : card.getFormats()) {
				testCard.getFormats().add(format.getName());
			}	
			cardsReturn.add(testCard);
		}
		
		return cardsReturn;

	}
	
	
	// partie admin 
	
	@Override
	public Card addCard(Card card, List<Color> cardColors, List<Format> cardFormats ) {
		
		for (Color color : cardColors) {
			
			card.getColors().add(colorRepository.findByName(color.getName()));
		}
		
		for (Format format : cardFormats) {
			
			card.getFormats().add(formatRepository.findByName(format.getName()));
		}
				
		return cardRepository.save(card);
	}
	
	
	@Override
	public Card addCardTest(FormCard cardRegister) {
		
		List <Color> colors = new ArrayList<>();
		List <Format> formats = new ArrayList<>();
		
		for (EnumColor enumColor : cardRegister.getColors()) {
			Color color = colorRepository.findByName(enumColor);
			colors.add(color);		
		}
		
		for (EnumFormat enumFormat : cardRegister.getFormats()) {
			Format format = formatRepository.findByName(enumFormat);
			formats.add(format);		
		}
		
		
		Card card = Card.builder().name(cardRegister.getName()).text(cardRegister.getText()).image(cardRegister.getImage())
					.manaCost(cardRegister.getManaCost()).value(cardRegister.getValue()).colors(colors).formats(formats)
					.type(cardRegister.getType()).legendary(cardRegister.getLegendary()).rarity(cardRegister.getRarity())
					.edition(cardRegister.getEdition()).likeNumber((long) 0).deckNumber((long) 0).commanderNumber((long) 0)
					.build();
		
		return cardRepository.save(card);
		
	}
	
	
	
	
	@Override 
	public Card updateCard (Long cardID, Card cardUpdate) {
		
		Optional<Card> card = cardRepository.findById(cardID);
		
		if(card.isPresent()) {
			
			Card newCard = card.get();	
				
			if( cardUpdate.getName() != null) {
				newCard.setName(cardUpdate.getName()); }
			if(cardUpdate.getText() != null) {
				newCard.setText(cardUpdate.getText()); }
			if( cardUpdate.getManaCost() != null) {
				newCard.setManaCost(cardUpdate.getManaCost()); }
			if( cardUpdate.getValue() != null) {
				newCard.setValue(cardUpdate.getValue()); }
			if( cardUpdate.getFormats() != null) {
				newCard.setFormats(cardUpdate.getFormats()); }
			if( cardUpdate.getEdition() != null) {
				newCard.setEdition(cardUpdate.getEdition()); }
			if( cardUpdate.getRarity() != null) {
				newCard.setRarity(cardUpdate.getRarity()); }
			if(cardUpdate.getImage() != null) {
				newCard.setImage(cardUpdate.getImage()); }
			
			return cardRepository.save(newCard);
		}
		throw new RuntimeException("Deck non trouvé");
	}
	
	@Override
	public String deleteCard(Long cardID) {	
		
		Optional<Card> card = cardRepository.findById(cardID); 
		
		if(card.isPresent()) {
			
			Card cardFind = card.get();
					
			for (Deck deckTarget : cardFind.getDecks() )
			{
				deckTarget.getCards().remove(cardFind);
				deckRepository.save(deckTarget);
			}
					
			cardRepository.deleteById(cardID);
			return "Carte numéro " + cardID + " supprimée";
		}
		throw new RuntimeException("Carte non trouvée");
	}
		
	
	
}
