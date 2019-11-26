package app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.controllers.params.CardParams;
import app.controllers.params.FilterParams;
import app.controllers.views.CardSummaryView;
import app.controllers.views.CardView;
import app.model.Card;
import app.model.views.CardSummary;
import app.services.CardsService;

@RestController
@RequestMapping(path = "/decks/{deckId}/cards",
		produces = {
				MediaType.APPLICATION_JSON_UTF8_VALUE,
				MediaType.APPLICATION_XML_VALUE })
public class CardsController {
	@Autowired
	private CardsService cardsService;
	
	
	@GetMapping
	public ResponseEntity<List<CardView>> getCards(
			@PathVariable int deckId,
			@Valid FilterParams filterParams) {
		List<Card> cards = cardsService.getCards(
				deckId,
				filterParams.getDescriptionFilter(),
				filterParams.getFirstResult(),
				filterParams.getMaxResults());
		return ResponseEntity.ok(CardView.buildFromCards(cards));
	}
	
	@GetMapping(path = "/summaries")
	public ResponseEntity<List<CardSummaryView>> getCardSummaries(
			@PathVariable int deckId,
			@Valid FilterParams filterParams) {
		List<CardSummary> cardSummaries = cardsService.getCardSummaries(
				deckId,
				filterParams.getDescriptionFilter(),
				filterParams.getFirstResult(),
				filterParams.getMaxResults());
		return ResponseEntity.ok(CardSummaryView.buildFromCardSummaries(cardSummaries));
	}
	
	@GetMapping(path = "/{cardId}")
	public ResponseEntity<CardView> getCard(
			@PathVariable int deckId,
			@PathVariable int cardId) {
		Optional<Card> optionalCard = cardsService.getCard(
				deckId,
				cardId);
		return ResponseEntity.of(CardView.buildFromCard(optionalCard));
	}
	
	@PostMapping
	public CardView createCard(
			@PathVariable int deckId,
			@Valid CardParams cardParams) {
		Card card = cardsService.createCard(cardParams.buildCard(deckId));
		return CardView.buildFromCard(card);
	}
	
	@PutMapping(path = "/{cardId}")
	public ResponseEntity<?> updateCard(
			@PathVariable int deckId,
			@PathVariable int cardId,
			@Valid CardParams cardParams) {
		Optional<Card> optionalCard = cardsService.getCard(
				deckId,
				cardId);
		if (optionalCard.isPresent()) {
			cardsService.updateCard(cardParams.mergeCard(optionalCard.get()));
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(path = "/{cardId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCard(
			@PathVariable int deckId,
			@PathVariable int cardId) {
		cardsService.deleteCard(
				deckId,
				cardId);
	}
	
	/*
	public void deleteCards() {
		// TODO:
		// https://stackoverflow.com/questions/2421595/restful-way-for-deleting-a-bunch-of-items
	}
	*/
}
