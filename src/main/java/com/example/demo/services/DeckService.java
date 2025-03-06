package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeckCreator;
import com.example.demo.entities.Format;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import com.example.demo.enums.UserActivity;
import com.example.demo.form.FormDeck;
import com.example.demo.register.GetCard;
import com.example.demo.register.GetDeck;
import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Deck;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.ColorRepository;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.repositories.DeckRepository;
import com.example.demo.repositories.FormatRepository;


@Service
public class DeckService implements IDeckService {
	
	@Autowired
	DeckBuilderRepository deckBuilderRepository;
	
	@Autowired
	DeckRepository deckRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private FormatRepository formatRepository;
	
	
	// Méthodes f_all
	
	// homePage
	
		@Override
		public List<GetDeck> getTopDecks() {
			
			List<Deck> decks = deckRepository.findAll();
			List<Deck> topDecks = new ArrayList<>();
			
			for (Deck deck : decks) {
				if(deck.getLikeNumber() != null) {
					topDecks.add(deck);
				}
				
			}
			
			topDecks.sort(Comparator.comparingLong(Deck::getLikeNumber).reversed());
			List<GetDeck> topGetDecks = new ArrayList<>();
			
			for (Deck deck : topDecks) {
				GetDeck testDeck = new GetDeck();
				testDeck.setId(deck.getId());
				testDeck.setName(deck.getName());
				testDeck.setImage(deck.getImage());
				testDeck.setDateCreation(deck.getDateCreation());
				testDeck.setValue(deck.getValue());
				testDeck.setManaCost(deck.getManaCost());
				testDeck.setFormat(deck.getFormat());
				testDeck.setLikeNumber(deck.getLikeNumber());
				testDeck.setDeckBuilderName(deck.getDeckBuilder().getPseudo());
				testDeck.setLikeNumber(deck.getLikeNumber());
				
				for (Color color : deck.getColors()) {
					testDeck.getColors().add(color.getName());
				}	
				topGetDecks.add(testDeck);
			}
			
			return topGetDecks;
		}
		
		@Override
		public List<GetDeck> getTop3Decks() {
			
			List<Deck> decks = deckRepository.findAll();
			List<Deck> topDecks = new ArrayList<>();
			
			for (Deck deck : decks) {
				if(deck.getLikeNumber() != null) {
					topDecks.add(deck);
				}
				
			}
			
			topDecks.sort(Comparator.comparingLong(Deck::getLikeNumber).reversed());
			List<GetDeck> topGetDecks = new ArrayList<>();
			
			for (Deck deck : topDecks) {
				GetDeck testDeck = new GetDeck();
				testDeck.setId(deck.getId());
				testDeck.setName(deck.getName());
				testDeck.setImage(deck.getImage());
				testDeck.setDateCreation(deck.getDateCreation());
				testDeck.setValue(deck.getValue());
				testDeck.setManaCost(deck.getManaCost());
				testDeck.setFormat(deck.getFormat());
				testDeck.setLikeNumber(deck.getLikeNumber());
				testDeck.setDeckBuilderName(deck.getDeckBuilder().getPseudo());
				testDeck.setLikeNumber(deck.getLikeNumber());
				
				for (Color color : deck.getColors()) {
					testDeck.getColors().add(color.getName());
				}	
				if(topGetDecks.size() == 3) {
					break; }
				topGetDecks.add(testDeck);
			}
			
			return topGetDecks;
		}
		
	@Override
	public List<GetDeck> getDecksByFilter (String name, Long manaCostMin, Long manaCostMax, 
			Float valueMin,	Float valueMax, List<EnumFormat> formats, List<EnumColor> colors) {
		
		List<Color> colorsEntities = new ArrayList<>();	
		if(colors != null) {
			for (EnumColor color : colors) {	
				colorsEntities.add(colorRepository.findByName(color));
			}
		}
		else {
			colorsEntities = null;
		}
		List<Deck> decks = deckRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax, false, formats, colorsEntities);
		List<GetDeck> decksReturn = new ArrayList<>();

		for (Deck deck : decks) {
			GetDeck testDeck = new GetDeck();
			testDeck.setId(deck.getId());
			testDeck.setName(deck.getName());
			testDeck.setImage(deck.getImage());
			testDeck.setDateCreation(deck.getDateCreation());
			testDeck.setValue(deck.getValue());
			testDeck.setManaCost(deck.getManaCost());
			testDeck.setFormat(deck.getFormat());
			testDeck.setLikeNumber(deck.getLikeNumber());
			testDeck.setDeckBuilderName(deck.getDeckBuilder().getPseudo());
			
			for (Color color : deck.getColors()) {
				testDeck.getColors().add(color.getName());
			}	
			decksReturn.add(testDeck);

		}
		
		return decksReturn;
	}


	@Override
	public GetDeck getDeckById(Long deckID) {
		 
		Deck deckFind = deckRepository.findById(deckID).get();
		 GetDeck deckReturn = new GetDeck();

		 if (deckFind != null) {			 
			 deckReturn.setId(deckFind.getId());
			 deckReturn.setName(deckFind.getName());
			 deckReturn.setImage(deckFind.getImage());
			 deckReturn.setDateCreation(deckFind.getDateCreation());
			 deckReturn.setValue(deckFind.getValue());
			 deckReturn.setManaCost(deckFind.getManaCost());
			 deckReturn.setFormat(deckFind.getFormat());
			 deckReturn.setLikeNumber(deckFind.getLikeNumber());
			 deckReturn.setDeckBuilderName(deckFind.getDeckBuilder().getPseudo());
				
			for (Color color : deckFind.getColors()) {
					deckReturn.getColors().add(color.getName());
				};
		 }
				return deckReturn;
			
		 }
	
	
	
	@Override
	public List<GetCard> getCardsOnDeckById(Long deckID) {
		
		Deck deck = deckRepository.findById(deckID).get();
		List<Card> CardsOnDeck = deck.getCards();
		
		List<GetCard> CardsReturn = new ArrayList<>();
		
		for (Card cardFind : CardsOnDeck) {
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
			 CardsReturn.add(cardReturn);
		}
			return CardsReturn;
	}
		
	// Méthodes f_user
	
	@Override
	public Set<GetDeck> getDecksByUser(DeckCreator dbuilder) {
		
		Set<Deck> decksUser = deckRepository.findByDeckBuilder(dbuilder);
		Set<GetDeck> decksReturn = new HashSet<>();

		for (Deck deck : decksUser) {
			GetDeck testDeck = new GetDeck();
			testDeck.setId(deck.getId());
			testDeck.setName(deck.getName());
			testDeck.setImage(deck.getImage());
			testDeck.setDateCreation(deck.getDateCreation());
			testDeck.setValue(deck.getValue());
			testDeck.setManaCost(deck.getManaCost());
			testDeck.setFormat(deck.getFormat());
			testDeck.setLikeNumber(deck.getLikeNumber());
			testDeck.setDeckBuilderName(deck.getDeckBuilder().getPseudo());
			
			for (Color color : deck.getColors()) {
				testDeck.getColors().add(color.getName());
			}	
			decksReturn.add(testDeck);

		}
		return decksReturn;
	}
	
	
	@Override
	public Long addDeckWithForm (DeckCreator dbuilder, FormDeck deckRegister ) {
		
		
		if(dbuilder != null) {
			
			Deck deck = new Deck();
			deck.setName(deckRegister.getName());
			deck.setDateCreation(LocalDate.now());
			deck.setImage(deckRegister.getImage());
			deck.setFormat(deckRegister.getFormat());
			deck.setIsPublic(false);
			deck.setDeckBuilder(dbuilder);
			
			for (EnumColor color : deckRegister.getColors()) {
				Color newColor = colorRepository.findByName(color);
				deck.getColors().add(newColor);
			}
			
				
			deckRepository.save(deck);
			
			dbuilder.setActivity(UserActivity.CREATOR);
			//dbuilder.getDecks().add(deck);
			deckBuilderRepository.save(dbuilder);
			
			
			return deck.getId();

		}
		throw new RuntimeException("Utilisateur non trouvé");
			
	}
	
	
	@Override
	public String deleteDeck(Long deckID) {
		
		Optional<Deck> deck = deckRepository.findById(deckID);

		if(deck.isPresent()) {
			
			Deck deckFind = deck.get();
			List <DeckCreator> dbs = deckBuilderRepository.findAll();
			
			for (DeckCreator deckCreator : dbs) {
				if (deckCreator.getDecksLiked().contains(deckFind)) {
					deckCreator.getDecksLiked().remove(deckFind);
					deckBuilderRepository.save(deckCreator);
				}
			}
			
			deckRepository.deleteById(deckID);
			
			return "Deck " + deckFind.getName() + " supprimé";
		}
		throw new RuntimeException("Deck non trouvé");
	}
	
	
	@Override 
	public Deck updateDeck (Long deckID, Deck deckUpdate) {
		
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		if(deck.isPresent()) {
			
			Deck newDeck = deck.get();
			
			List<Card> cardCompatible = newDeck.getCards().stream()
					.filter(card -> card.getFormats().contains(deckUpdate.getFormat()) 
					&& card.getColors().stream().anyMatch(color -> deckUpdate.getColors().contains(color)))
					.collect(Collectors.toList());		
			// Filtre la list de cartes pour ne collecter que les cartes dont la list de format contient le format de deckUpdate
			// Et dont la list de couleurs matche une fois avec la list de couleur de deckUpdate (anyMatch
			
			newDeck.setCards(cardCompatible);
			if(deckUpdate.getFormat() != null) {
				newDeck.setFormat(deckUpdate.getFormat()); }
			if(deckUpdate.getColors() != null) {
				newDeck.setColors(deckUpdate.getColors()); }
			if(deckUpdate.getName() != null) {
				newDeck.setName(deckUpdate.getName()); }
			if(deckUpdate.getImage() != null) {
				newDeck.setImage(deckUpdate.getImage()); }
			
			return deckRepository.save(newDeck);
		}
		throw new RuntimeException("Deck non trouvé");
	}

	
	@Override
	public List<Card> getCardsByFilterForDeck (Long deckId, String name, Long manaCostMin, Long manaCostMax,
			Float valueMin, Float valueMax, List<CardType> types, String legendary,
			List <EnumRarity> rarities, List<EnumEdition> editions) {
		
		Optional<Deck> deck = deckRepository.findById(deckId);
		
		List<Color> colorsDeck = new ArrayList<>();
		colorsDeck.addAll(deck.get().getColors());
		
		
		List<Format> formatDeck = new ArrayList<>();
		formatDeck.add(formatRepository.findByName(deck.get().getFormat()));
		
		
		if(deck.isPresent()) {
			
			return cardRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax, types, 
					legendary, rarities, editions, colorsDeck, formatDeck);
			
		}
		throw new RuntimeException("Deck non trouvé");
	}
	// Filtre les cartes comme getCartesFilter sauf que le format et la couleur sont deja définit 
	// pour correspondre au format et aux couleurs du deck
	
/*	
	@Override
	public List<Card> getCommanderByFilterForDeck (Long deckId, String name, Long manaCostMin, Long manaCostMax,
			Float valueMin, Float valueMax, List<EnumRarity> rarities, List<EnumEdition> editions) {
		
		Optional<Deck> deck = deckRepository.findById(deckId);
		
		List<CardType> enumCommander = new ArrayList<>();
		enumCommander.add(CardType.CREATURE_LEGENDAIRE);
		
		List<Color> colorsDeck = new ArrayList<>();
		colorsDeck.addAll(deck.get().getColors());
		
		
		List<Format> formatDeck = new ArrayList<>();
		formatDeck.add(formatRepository.findByName(deck.get().getFormat()));
		
		
		if(deck.isPresent()) {
			
			return cardRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax, enumCommander, 
					rarities, editions, colorsDeck, formatDeck);
			
		}
		throw new RuntimeException("Deck non trouvé");
		
	}
	*/
	
	@Override
	public Deck addCardOnDeck(Long cardId, Long deckId) {
		
		Optional<Card> cardToAdd = cardRepository.findById(cardId);
		Optional<Deck> deckToTarget = deckRepository.findById(deckId);
		
		if(cardToAdd.isPresent() && deckToTarget.isPresent()) {
			
			boolean communColor = false;
			
			for (Color deckColor : deckToTarget.get().getColors()) {
				for (Color cardColor : cardToAdd.get().getColors()) {
					if(deckColor.equals(cardColor)) {
						communColor = true;
						break;
					}
				}
			}
			
			boolean communFormat = false;
			
			for (Format formatCard : cardToAdd.get().getFormats()) {
				if(formatCard.getName().equals(deckToTarget.get().getFormat())) {
					communFormat = true;
					break;
				}
			}
			
				if(communFormat == true && communColor == true	) {
				/*
				if(deckToTarget.get().getFormat().equals(EnumFormat.COMMANDER.toString()) ) {
						if ( deckToTarget.get().getCards().size() > 99) {
									
							throw new RuntimeException("Nombre de cartes maximum atteint");
							}
						
			Card commanderPresent = deckToTarget.get().getCommander();
				for (Card cardPresent : deckToTarget.get().getCards()) {
			           if (cardPresent.getName().equals(cardToAdd.get().getName()) || commanderPresent.getName().equals(cardToAdd.get().getName()) ) {
			                	
		                    throw new RuntimeException("Carte déjà présente");
			                }
			            }
						
				}
				
				int count = 0;
	            for (Card cardPresent : deckToTarget.get().getCards()) {
	                if (cardPresent.getName().equals(cardToAdd.get().getName())) {
	                    count++;
	                }
	            }

	            if (count >= 4) {
	                throw new RuntimeException("Carte déjà présente quatre fois");
	            }
				*/
					
					deckToTarget.get().getCards().add(cardToAdd.get());					
					deckToTarget.get().setManaCost(getDeckManaCost(deckToTarget.get().getId()));
					deckToTarget.get().setValue(getDeckValue(deckToTarget.get().getId()));
	
							
					return deckRepository.save(deckToTarget.get());
			
			}
			throw new RuntimeException("Couleur ou format de la carte incompatible avec ce deck");
		}
		throw new RuntimeException("Deck ou carte non trouvé");
		
	}
	// Ajoute une carte au deck si son format et sa couleurs sont compatibles avec ceux du deck
	// et si la list ne dépasse pas 100 cartes dans le cas d'un format commander
	// Appelle les fonctions getDeckManaCost et getDeckValue pour calculer ses valeurs une fois la carte ajoutée
	
	
	@Override
	public Deck addCardsOnDeck(List<Long> cardsId, Long deckId) {
		
		Optional<Deck> deckToTarget = deckRepository.findById(deckId);
		
		for (Long cardId : cardsId) {
		
			Optional<Card> cardToAdd = cardRepository.findById(cardId);
			
			
		
			if(cardToAdd.isPresent() && deckToTarget.isPresent()) {
				
				System.out.println(cardToAdd.get().getName());
				
				boolean communColor = false;
			
					for (Color deckColor : deckToTarget.get().getColors()) {
						for (Color cardColor : cardToAdd.get().getColors()) {
							if(deckColor.equals(cardColor)) {
								communColor = true;
								System.out.println(communColor);
								break;
					}
				}
			}
			
				boolean communFormat = false;
			
				for (Format formatCard : cardToAdd.get().getFormats()) {
					if(formatCard.getName().equals(deckToTarget.get().getFormat())) {
						communFormat = true;
						System.out.println(communFormat);
						break;
					}
				}
			
						if(communFormat == true && communColor == true	) {
											
							deckToTarget.get().getCards().add(cardToAdd.get());					
							deckToTarget.get().setManaCost(getDeckManaCost(deckToTarget.get().getId()));
							deckToTarget.get().setValue(getDeckValue(deckToTarget.get().getId()));
			
									
							deckRepository.save(deckToTarget.get());
					
					}
						else { throw new RuntimeException("Couleur ou format de la carte incompatible avec ce deck");
							}
					}
			
			else {
		
			throw new RuntimeException("Deck non trouvé");
			}
		
		}
		
		return deckToTarget.get();
		
	}
	
	@Override
	public Deck addCommanderOnDeck(Long cardId, Long deckId) {
			
			Optional<Card> cardToAdd = cardRepository.findById(cardId);
			Optional<Deck> deckToTarget = deckRepository.findById(deckId);
			
			if(cardToAdd.isPresent() && deckToTarget.isPresent()) {
				if(cardToAdd.get().getLegendary().equals("legendary") && deckToTarget.get().getFormat().equals(EnumFormat.COMMANDER.toString()) ) {
					if(deckToTarget.get().getFormat().equals(null)) {
							deckToTarget.get().setCommander(cardToAdd.get());
					}
					throw new RuntimeException("Commandant deja présent");
				}
				throw new RuntimeException("Couleur ou format de la carte incompatible avec ce deck");
			}
			throw new RuntimeException("Deck ou carte non trouvé");
		
			
	}
	
	// Les cartes commanders ne peuvent etre que des créatures légendaires
	
	@Override
	public String deleteCardOnDeck(Long cardID, Long deckID) {
		
		Optional<Deck> deckToTarget = deckRepository.findById(deckID);
		Optional<Card> card = cardRepository.findById(cardID); 

		if(deckToTarget.isPresent()) {
			Card cardFind = card.get();
			
			for (Deck deckTarget : cardFind.getDecks() )
			{
				deckTarget.getCards().remove(cardFind);
				deckRepository.save(deckTarget);
			}
			return cardFind.getName() + " a été retiré du deck";
		}
		throw new RuntimeException("Deck non trouvé");
	}
	
	@Override
	public String deleteCommanderOnDeck(Long deckID) {
		
		Optional<Deck> deckToTarget = deckRepository.findById(deckID);
		
		if(deckToTarget.isPresent()) {
			if(!deckToTarget.get().getCommander().equals(null)) {
				deckToTarget.get().setCommander(null);
			}
			
			throw new RuntimeException("Aucun commander présent");
		}
		throw new RuntimeException("Deck non trouvé");
		
	}
	
	@Override
	public String publishDeck (Long deckID) {
		
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		if(deck.isPresent()) {

			if(deck.get().getFormat().equals(EnumFormat.COMMANDER.toString()) && deck.get().getCards().size() == 100 ||
				!deck.get().getFormat().equals(EnumFormat.COMMANDER.toString()) && deck.get().getCards().size() > 59
					) {
				Deck deckPresent = deck.get();
				deckPresent.setIsPublic(true);
				deckRepository.save(deckPresent);
				
				DeckCreator db = deckPresent.getDeckBuilder();
				db.setActivity(UserActivity.PUBLISHER);
				deckBuilderRepository.save(db);
				
				 return "Deck " + deckPresent.getName() + " publié !";
			}
			
			throw new RuntimeException("Ce deck ne contient pas suffisament de cartes pour etre jouable dans ce format");
		}
		
		throw new RuntimeException("Deck non trouvé");
	}
	// Publie un deck après avoir vérifié s'il a le bon nom de cartes pour etre jouable
	// (100 pour un commander, 60 ou + pour les autres formats)
	
	
	@Override
	public String privateDeck (Long deckID) {
		
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		if(deck.isPresent()) {
			if(deck.get().getIsPublic().equals(true)) {
				
				Deck deckPresent = deck.get();
				deckPresent.setIsPublic(false);
				deckRepository.save(deckPresent);
				return " deck " + deckPresent.getName() + " en privé";
			}
			
			throw new RuntimeException("Ce deck est deja en privé");
		}
		throw new RuntimeException("Deck non trouvé");
	}
	
	
	@Override
	public Float getDeckValue(Long deckID) {
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		Float deckValue = 0f;
		
		if(deck.isPresent()) {
			 List<Card> cardsDeck = deck.get().getCards();
			 for (Card cards : cardsDeck) {
				 if(!cards.getType().equals(CardType.TERRAIN) ) {
					 Float cardValue = cards.getValue();
					 deckValue += cardValue;
				 }
				
			}
		return deckValue;
		}
		throw new RuntimeException("Deck non trouvé");
	}
	// Donne la somme des valeurs des cartes du deck pour obtenir son cout total 
	
	
	
	@Override
	public Float getDeckManaCost(Long deckID) {
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		int deckManaCost = 0;
		float deckManaCostMoy = 0;
		int i = 0;
		
		if(deck.isPresent()) {
			 List<Card> cardsDeck = deck.get().getCards();
			 for (Card cards : cardsDeck) {
				 Long cardValue = cards.getManaCost();
				 if(!cards.getType().equals(CardType.TERRAIN) ) {
					 i++;
					 deckManaCost += cardValue;	
				 	deckManaCostMoy = deckManaCost / i;
				 }
			}
			
		return deckManaCostMoy;
		}
		throw new RuntimeException("Deck non trouvé");
	}
	// Fais une moyenne du cout en Mana des cartes du deck qui ne sont pas des terrains
	
	
	
	@Override
	public List<EnumColor> getDeckColors (Long deckID) { 
		
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		if(deck.isPresent()) {
			List<EnumColor> deckColors = new ArrayList<>();
			
			for (Color color : deck.get().getColors()) {
				EnumColor colorName = color.getName();
				deckColors.add(colorName);				
			}
			
			return deckColors;
		}
		throw new RuntimeException("Deck non trouvé");
	}
			
			 
			 
			
	

}
