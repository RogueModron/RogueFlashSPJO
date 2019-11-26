package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.daos.CardInstancesDao;
import app.daos.CardPlansDao;
import app.daos.CardsDao;
import app.model.Card;
import app.model.CardInstance;
import app.model.CardPlan;
import app.model.views.CardSummary;

@Service
@Transactional
public class CardsService {
	@Autowired
	private CardsDao cardsDao;
	
	@Autowired
	private CardInstancesDao cardInstancesDao;
	
	@Autowired
	private CardPlansDao cardPlansDao;
	
	
	public Card createCard(Card card) {
		card = cardsDao.createCard(card);
		
		createCardInstance(
				card.getId(),
				true);
		createCardInstance(
				card.getId(),
				false);
		
		return card;
	}
	
	public Card updateCard(Card card) {
		return cardsDao.updateCard(card);
	}
	
	public void deleteCard(
			int deckId,
			int cardId) {
		cardsDao.deleteCard(
				deckId,
				cardId);
	}
	
	public void deleteCards(
			int deckId,
			int cardId,
			List<Integer> ids) {
		cardsDao.deleteCards(
				deckId,
				cardId,
				ids);
	}
	
	@Transactional(readOnly = true)
	public Optional<Card> getCard(
			int deckId,
			int cardId) {
		return cardsDao.getCard(
				deckId,
				cardId);
	}
	
	@Transactional(readOnly = true)
	public List<Card> getCards(
			int deckId,
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		return cardsDao.getCards(
				deckId,
				descriptionFilter,
				firstResult,
				maxResults);
	}
	
	@Transactional(readOnly = true)
	public List<CardSummary> getCardSummaries(
			int deckId,
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		return cardsDao.getCardSummaries(
				deckId,
				descriptionFilter,
				firstResult,
				maxResults);
	}
	
	
	private CardInstance createCardInstance(
			int cardId,
			boolean sideAToB) {
		CardInstance cardInstance = CardInstance.builder()
			.cardId(cardId)
			.sideAToB(sideAToB)
			.build();
		cardInstance = cardInstancesDao.createCardInstance(cardInstance);

		CardPlan cardPlan = CardPlan.builder()
			.cardInstanceId(cardInstance.getId())
			.build();
		cardPlansDao.createCardPlan(cardPlan);
		
		return cardInstance;
	}
}
