package app.daos;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.daos.CardsDao;
import app.daos.DecksDao;
import app.model.Card;
import app.model.Deck;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCardsDao {
	@Autowired
	private CardsDao cardsDao;
	
	@Autowired
	private DecksDao decksDao;
	
	@Test
	public void getCard() {
		Deck deckToCreate = Deck.builder().description("Test Get Card").build();
		Deck createdDeck = decksDao.createDeck(deckToCreate);
		
		Card cardToCreate = Card.builder()
				.deckId(createdDeck.getId())
				.sideA("Side A")
				.sideB("Side B")
				.build();
		Card createdCard = cardsDao.createCard(cardToCreate);
		
		Optional<Card> card = cardsDao.getCard(
				createdDeck.getId(),
				createdCard.getId());
		assertTrue(card.isPresent());
	}
	
	@Test(expected = Test.None.class)
	public void getCardSummaries() {
		cardsDao.getCardSummaries(1, "Test", 0, 10);
	}
}
