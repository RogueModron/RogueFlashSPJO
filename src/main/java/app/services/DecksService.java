package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.daos.DecksDao;
import app.model.Deck;
import app.model.views.DeckSummary;

@Service
@Transactional
public class DecksService {
	@Autowired
	private DecksDao decksDao;
	
	
	public Deck createDeck(Deck deck) {
		return decksDao.createDeck(deck);
	}
	
	public Deck updateDeck(Deck deck) {
		return decksDao.updateDeck(deck);
	}
	
	public void deleteDeck(int id) {
		decksDao.deleteDeck(id);
	}
	
	public void deleteDecks(List<Integer> ids) {
		decksDao.deleteDecks(ids);
	}
	
	@Transactional(readOnly = true)
	public Optional<Deck> getDeck(int id) {
		return decksDao.getDeck(id);
	}
	
	@Transactional(readOnly = true)
	public List<Deck> getDecks(
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		return decksDao.getDecks(
				descriptionFilter,
				firstResult,
				maxResults);
	}
	
	@Transactional(readOnly = true)
	public List<DeckSummary> getDeckSummaries(
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		return decksDao.getDeckSummaries(
				descriptionFilter,
				firstResult,
				maxResults);
	}
}
