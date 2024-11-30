package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.Rarity;
import com.example.demo.enums.UserActivity;
import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.repositories.DeckRepository;


@Service
public class DeckService implements IDeckService {
	
	@Autowired
	DeckBuilderRepository deckBuilderRepository;
	
	@Autowired
	DeckRepository deckRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	public Deck addDeck(Long userId, Deck deck) {
		Optional<DeckCreator> dbuilder = deckBuilderRepository.findById(userId);
		
		if(dbuilder.isPresent()) {
		
		DeckCreator db  = dbuilder.get();
		Deck d = Deck.builder().name(deck.getName()).format(deck.getFormat()).colors(deck.getColors())
				.isPublic(false).image(deck.getImage()).deckBuilder(db)
				.build();
		
		db.setActivity(UserActivity.CREATOR);
		
		deckBuilderRepository.save(db);	
		return deckRepository.save(d); 
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	// Créé un deck pour un user 
	
	
	@Override
	public String deleteDeck(Long deckID) {
		
		Optional<Deck> deck = deckRepository.findById(deckID);

		if(deck.isPresent()) {
			
			deckRepository.deleteById(deckID);
			
			return "Deck " + deck.get().getName() + " supprimé";
		}
		throw new RuntimeException("Deck non trouvé");
	}
	
	
	@Override 
	public Deck updateDeck (Long deckID, Deck deckUpdate) {
		
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		if(deck.isPresent()) {
			
			Deck newDeck = deck.get();
			
			List<Card> cardCompatible = newDeck.getCartes().stream()
					.filter(card -> card.getFormats().contains(deckUpdate.getFormat()) 
					&& card.getColors().stream().anyMatch(color -> deckUpdate.getColors().contains(color)))
					.collect(Collectors.toList());		
			// Filtre la list de cartes pour ne collecter que les cartes dont la list de format contient le format de deckUpdate
			// Et dont la list de couleurs matche une fois avec la list de couleur de deckUpdate (anyMatch
			
			newDeck.setCartes(cardCompatible);
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
			Float valueMin, Float valueMax, List<CardType> types,
			List <Rarity> rarities, List<EnumEdition> editions) {
		
		Optional<Deck> deck = deckRepository.findById(deckId);
		
		List<EnumFormat> formatDeck = new ArrayList<>();
		formatDeck.add(deck.get().getFormat());
		
		List<EnumColor> colorsDeck = new ArrayList<>();
		colorsDeck.addAll(deck.get().getColors());
		
		if(deck.isPresent()) {
			
			return cardRepository.findByOptionalAttribute(name,manaCostMin, manaCostMax, valueMin, valueMax,
			formatDeck, colorsDeck, types, rarities, editions);
			
		}
		throw new RuntimeException("Deck non trouvé");
	}
	// Filtre les cartes comme getCartesFilter sauf que le format et la couleur sont deja définit 
	// pour correspondre au format et aux couleurs du deck
	
	@Override
	public List<Card> getCommanderByFilterForDeck (Long deckId, String name, Long manaCostMin, Long manaCostMax,
			Float valueMin, Float valueMax, List<Rarity> rarities, List<EnumEdition> editions) {
		
		Optional<Deck> deck = deckRepository.findById(deckId);
		List<Card> cardsFind = null;
		
		List<CardType> enumCommander = new ArrayList<>();
		enumCommander.add(CardType.CREATURE_LEGENDAIRE);
		
		List<EnumFormat> formatDeck = new ArrayList<>();
		formatDeck.add(deck.get().getFormat());
		
		List<EnumColor> colorsDeck = new ArrayList<>();
		colorsDeck.addAll(deck.get().getColors());
		
		if(deck.isPresent()) {
				
			cardsFind = cardRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax,
			formatDeck, colorsDeck, enumCommander, rarities, editions);
			
			return cardsFind;
		}
		throw new RuntimeException("Deck non trouvé");
		
		
		
		
	}
	
	@Override
	public Deck addCardOnDeck(Long cardId, Long deckId) {
		
		Optional<Card> cardToAdd = cardRepository.findById(cardId);
		Optional<Deck> deckToTarget = deckRepository.findById(deckId);
		
		if(cardToAdd.isPresent() && deckToTarget.isPresent()) {
			
			boolean communColor = false;
			
			for (EnumColor deckColor : deckToTarget.get().getColors()) {
				for (EnumColor cardColor : cardToAdd.get().getColors()) {
					if(deckColor.equals(cardColor)) {
						communColor = true;
						break;
					}
				}
			}
			
			if(cardToAdd.get().getFormats().contains(deckToTarget.get().getFormat()) && communColor == true	) {
				
				if(deckToTarget.get().getFormat().equals(EnumFormat.COMMANDER.toString()) ) {
						if ( deckToTarget.get().getCartes().size() > 99) {
									
							throw new RuntimeException("Nombre de cartes maximum atteint");
							}
						Card commanderPresent = deckToTarget.get().getCommander();
						for (Card cardPresent : deckToTarget.get().getCartes()) {
			                if (cardPresent.getName().equals(cardToAdd.get().getName()) || commanderPresent.getName().equals(cardToAdd.get().getName()) ) {
			                	
			                    throw new RuntimeException("Carte déjà présente");
			                }
			            }
						
				}
				
				int count = 0;
	            for (Card cardPresent : deckToTarget.get().getCartes()) {
	                if (cardPresent.getName().equals(cardToAdd.get().getName())) {
	                    count++;
	                }
	            }

	            if (count >= 4) {
	                throw new RuntimeException("Carte déjà présente quatre fois");
	            }
				
					{
						deckToTarget.get().getCartes()
						.add(cardToAdd.get());
						
						deckToTarget.get().setManaCost(getDeckManaCost(deckToTarget.get().getId()));
						deckToTarget.get().setValue(getDeckValue(deckToTarget.get().getId()));

						
						return deckRepository.save(deckToTarget.get());
				}
			}
			throw new RuntimeException("Couleur ou format de la carte incompatible avec ce deck");
		}
		throw new RuntimeException("Deck ou carte non trouvé");
		
	}
	// Ajoute une carte au deck si son format et sa couleurs sont compatibles avec ceux du deck
	// et si la list ne dépasse pas 100 cartes dans le cas d'un format commander
	// Appelle les fonctions getDeckManaCost et getDeckValue pour calculer ses valeurs une fois la carte ajoutée
	
	@Override
	public Deck addCommanderOnDeck(Long cardId, Long deckId) {
			
			Optional<Card> cardToAdd = cardRepository.findById(cardId);
			Optional<Deck> deckToTarget = deckRepository.findById(deckId);
			
			if(cardToAdd.isPresent() && deckToTarget.isPresent()) {
				if(cardToAdd.get().getType().equals(CardType.CREATURE_LEGENDAIRE.toString()) && deckToTarget.get().getFormat().equals(EnumFormat.COMMANDER.toString()) ) {
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
				deckTarget.getCartes().remove(cardFind);
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

			if(deck.get().getFormat().equals(EnumFormat.COMMANDER.toString()) && deck.get().getCartes().size() == 100 ||
				!deck.get().getFormat().equals(EnumFormat.COMMANDER.toString()) && deck.get().getCartes().size() > 59
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
	public Set<Deck> getDeckByUser(Long dbID) {
		Optional<DeckCreator> dbuilder = deckBuilderRepository.findById(dbID);
		
		if(dbuilder.isPresent()) {
			
			return dbuilder.get().getDecks();
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	// Récupère les decks pour un user
	
	
	@Override
	public Float getDeckValue(Long deckID) {
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		Float deckValue = 0f;
		
		if(deck.isPresent()) {
			 List<Card> cardsDeck = deck.get().getCartes();
			 for (Card cards : cardsDeck) {
				 Float cardValue = cards.getValue();
				 deckValue += cardValue;
				
			}
		return deckValue;
		}
		throw new RuntimeException("Utilisateur non trouvé");
	}
	// Donne la somme des valeurs des cartes du deck pour obtenir son cout total 
	
	
	
	@Override
	public Float getDeckManaCost(Long deckID) {
		Optional<Deck> deck = deckRepository.findById(deckID);
		
		int deckManaCost = 0;
		float deckManaCostMoy = 0;
		int i = 0;
		
		if(deck.isPresent()) {
			 List<Card> cardsDeck = deck.get().getCartes();
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
		throw new RuntimeException("Utilisateur non trouvé");
	}
	// Fais une moyenne du cout en Mana des cartes du deck qui ne sont pas des terrains
	
	
	@Override
	public List<Deck> getDecksByFilter (String name, Long manaCostMin, Long manaCostMax, 
			Float valueMin,	Float valueMax, List<EnumFormat> formats, List<EnumColor> colors) {
		return deckRepository.findByOptionalAttribute(name, manaCostMin, manaCostMax, valueMin, valueMax, formats, colors, true);
	}
	

}
