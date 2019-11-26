package app.daos;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.daos.DecksDao;
import app.model.Deck;
import app.model.views.DeckSummary;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDecksDao {
	@Autowired
	private DecksDao decksDao;
	
	@Test
	public void createDeck() {
		Deck deckToCreate1 = Deck.builder().description("Test Create Deck 1").build();
		Deck createdDeck1 = decksDao.createDeck(deckToCreate1);
		
		Deck deckToCreate2 = Deck.builder().description("Test Create Deck 2").build();
		Deck createdDeck2 = decksDao.createDeck(deckToCreate2);
		
		assertTrue(
				createdDeck1.getId() > 0
				&& createdDeck2.getId() > 0
				&& createdDeck1.getId() != createdDeck2.getId());
	}
	
	@Test
	public void updateDeck() {
		Deck deckToCreate = Deck.builder().description("Test Update Deck").build();
		Deck createdDeck = decksDao.createDeck(deckToCreate);
		
		Deck deckToUpdate = new Deck(
				createdDeck.getId(),
				createdDeck.getVersion(),
				createdDeck.getDescription(),
				"Notes");
		decksDao.updateDeck(deckToUpdate);
		
		Optional<Deck> updatedDeck = decksDao.getDeck(deckToUpdate.getId());
		assertTrue(
				updatedDeck.isPresent()
				&& updatedDeck.get().getNotes().equals("Notes"));
	}
	
	@Test
	public void deleteDeck() {
		Deck deckToCreate = Deck.builder().description("Test Delete Deck").build();
		Deck createdDeck = decksDao.createDeck(deckToCreate);
		
		decksDao.deleteDeck(createdDeck.getId());
		
		Optional<Deck> deck = decksDao.getDeck(createdDeck.getId());
		assertTrue(!deck.isPresent());
	}
	
	@Test
	public void deleteDecks() {
		Deck deckToCreate1 = Deck.builder().description("Test Delete Decks 1").build();
		Deck createdDeck1 = decksDao.createDeck(deckToCreate1);
		
		Deck deckToCreate2 = Deck.builder().description("Test Delete Decks 2").build();
		Deck createdDeck2 = decksDao.createDeck(deckToCreate2);
		
		List<Integer> ids = Arrays.asList(
				createdDeck1.getId(),
				createdDeck2.getId());
		decksDao.deleteDecks(ids);
		
		Optional<Deck> deck1 = decksDao.getDeck(createdDeck1.getId());
		Optional<Deck> deck2 = decksDao.getDeck(createdDeck2.getId());
		
		assertTrue(
				!deck1.isPresent()
				&& !deck2.isPresent());
	}
	
	@Test
	public void getDeck() {
		Deck deckToCreate = Deck.builder().description("Test Get Deck").build();
		Deck createdDeck = decksDao.createDeck(deckToCreate);
		
		Optional<Deck> deck = decksDao.getDeck(createdDeck.getId());
		assertTrue(deck.isPresent());
	}

	@Test
	public void getDecks() {
		Deck deckToCreate = Deck.builder().description("Test Get Decks").build();
		decksDao.createDeck(deckToCreate);
		
		List<Deck> decks = decksDao.getDecks("Test", 0, 10);
		assertTrue(!decks.isEmpty());
	}
	
	@Test
	public void getDeckSummaries() {
		Deck deckToCreate = Deck.builder().description("Test Get DeckSummaries").build();
		decksDao.createDeck(deckToCreate);
		
		List<DeckSummary> decks = decksDao.getDeckSummaries("Test", 0, 10);
		assertTrue(!decks.isEmpty());
	}
}
