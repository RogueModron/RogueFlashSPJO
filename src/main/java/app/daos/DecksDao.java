package app.daos;

import static app.db.tables.Cards.CARDS;
import static app.db.tables.CardsInstances.CARDS_INSTANCES;
import static app.db.tables.Decks.DECKS;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.db.tables.records.DecksRecord;
import app.model.Deck;
import app.model.views.DeckSummary;

@Repository
@Transactional
public class DecksDao {
	@Autowired
	private DSLContext dsl;
	
	public Deck createDeck(Deck deck) {
		if (deck == null) {
			throw new IllegalArgumentException();
		}
		
		DecksRecord r = dsl.newRecord(DECKS, deck);
		r.insert();
		
		return r.into(Deck.class);
	}
	
	public Deck updateDeck(Deck deck) {
		if (deck == null) {
			throw new IllegalArgumentException();
		}
		
		DecksRecord r = dsl.newRecord(DECKS, deck);
		r.update();
		
		return r.into(Deck.class);
	}
	
	public void deleteDeck(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		
		dsl
			.deleteFrom(DECKS)
			.where(DECKS.ID.eq(id))
			.execute();
	}
	
	public void deleteDecks(List<Integer> ids) {
		if (ids == null
				|| ids.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		dsl
			.deleteFrom(DECKS)
			.where(DECKS.ID.in(ids))
			.execute();
	}
	
	@Transactional(readOnly = true)
	public Optional<Deck> getDeck(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		
		DecksRecord r = dsl
				.fetchAny(
						DECKS,
						DECKS.ID.eq(id));
		if (r == null) {
			return Optional.empty();
		} else {
			return Optional.of(r.into(Deck.class));
		}
	}
	
	@Transactional(readOnly = true)
	public List<Deck> getDecks(
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		if (descriptionFilter == null) {
			throw new IllegalArgumentException();
		}
		if (firstResult < 0) {
			throw new IllegalArgumentException();
		}
		if (maxResults <= 0) {
			throw new IllegalArgumentException();
		}

		return dsl
				.selectFrom(DECKS)
				.where(DECKS.DESCRIPTION.like("%" + descriptionFilter + "%"))
				.orderBy(
						DECKS.DESCRIPTION,
						DECKS.ID)
				.limit(maxResults)
				.offset(firstResult)
				.fetch().into(Deck.class);
	}
	
	@Transactional(readOnly = true)
	public List<DeckSummary> getDeckSummaries(
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		if (descriptionFilter == null) {
			throw new IllegalArgumentException();
		}
		if (firstResult < 0) {
			throw new IllegalArgumentException();
		}
		if (maxResults <= 0) {
			throw new IllegalArgumentException();
		}
		
		Table<Record2<Integer, Integer>> numberOfSidesTable = dsl
				.select(
						CARDS.DECK_ID.as("deckId"),
						DSL.count().as("numberOfSides"))
				.from(CARDS)
				.leftOuterJoin(CARDS_INSTANCES)
				.on(CARDS.ID.eq(CARDS_INSTANCES.CARD_ID))
				.where(
						CARDS_INSTANCES.DISABLED.isNull()
						.or(CARDS_INSTANCES.DISABLED.isFalse()))
				.groupBy(CARDS.DECK_ID)
				.asTable("numberOfSidesForDecks");
		
		return dsl
				.select(
						DECKS.ID.as("deckId"),
						DECKS.DESCRIPTION.as("description"),
						DECKS.NOTES.as("notes"),
						DSL.ifnull(numberOfSidesTable.field("numberOfSides"), 0).as("numberOfSides"))
				.from(DECKS)
				.leftOuterJoin(numberOfSidesTable)
				.on(DECKS.ID.eq(numberOfSidesTable.field("deckId", Integer.class)))
				.where(DECKS.DESCRIPTION.like("%" + descriptionFilter + "%"))
				.orderBy(
						DSL.value(2),
						DSL.value(4),
						DSL.value(1))
				.limit(maxResults)
				.offset(firstResult)
				.fetch().into(DeckSummary.class);
	}
}
