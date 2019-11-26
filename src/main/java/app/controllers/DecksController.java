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

import app.controllers.params.DeckParams;
import app.controllers.params.FilterParams;
import app.controllers.views.DeckSummaryView;
import app.controllers.views.DeckView;
import app.model.Deck;
import app.model.views.DeckSummary;
import app.services.DecksService;

@RestController
@RequestMapping(path = "/decks",
		produces = {
				MediaType.APPLICATION_JSON_UTF8_VALUE,
				MediaType.APPLICATION_XML_VALUE })
public class DecksController {
	@Autowired
	private DecksService decksService;
	
	
	@GetMapping
	public ResponseEntity<List<DeckView>> getDecks(@Valid FilterParams filterParams) {
		List<Deck> decks = decksService.getDecks(
				filterParams.getDescriptionFilter(),
				filterParams.getFirstResult(),
				filterParams.getMaxResults());
		return ResponseEntity.ok(DeckView.buildFromDecks(decks));
	}
	
	@GetMapping(path = "/summaries")
	public ResponseEntity<List<DeckSummaryView>> getDeckSummaries(@Valid FilterParams filterParams) {
		List<DeckSummary> deckSummaries = decksService.getDeckSummaries(
				filterParams.getDescriptionFilter(),
				filterParams.getFirstResult(),
				filterParams.getMaxResults());
		return ResponseEntity.ok(DeckSummaryView.buildFromDeckSummaries(deckSummaries));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<DeckView> getDeck(@PathVariable int id) {
		Optional<Deck> optionalDeck = decksService.getDeck(id);
		return ResponseEntity.of(DeckView.buildFromDeck(optionalDeck));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public DeckView createDeck(@Valid DeckParams deckParams) {
		Deck deck = decksService.createDeck(deckParams.buildDeck());
		return DeckView.buildFromDeck(deck);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateDeck(
			@PathVariable int id,
			@Valid DeckParams deckParams) {
		Optional<Deck> optionalDeck = decksService.getDeck(id);
		if (optionalDeck.isPresent()) {
			decksService.updateDeck(deckParams.mergeDeck(optionalDeck.get()));
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteDeck(@PathVariable int id) {
		decksService.deleteDeck(id);
	}
	
	/*
	public void deleteDecks() {
		// TODO:
		// https://stackoverflow.com/questions/2421595/restful-way-for-deleting-a-bunch-of-items
	}
	*/
}
