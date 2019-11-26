package app.daos;

import static app.db.tables.Cards.CARDS;
import static app.db.tables.CardsInstances.CARDS_INSTANCES;
import static app.db.tables.CardsPlans.CARDS_PLANS;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.db.tables.records.CardsRecord;
import app.model.Card;
import app.model.views.CardSummary;

@Repository
@Transactional
public class CardsDao {
	@Autowired
	private DSLContext dsl;
	
	public Card createCard(Card card) {
		if (card == null) {
			throw new IllegalArgumentException();
		}
		
		CardsRecord r = dsl.newRecord(CARDS, card);
		r.insert();
		
		return r.into(Card.class);
	}
	
	public Card updateCard(Card card) {
		if (card == null) {
			throw new IllegalArgumentException();
		}
		
		// Could change Deck, consider it a feature.
		
		CardsRecord r = dsl.newRecord(CARDS, card);
		r.update();
		
		return r.into(Card.class);
	}
	
	public void deleteCard(
			int deckId,
			int cardId) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
		if (cardId <= 0) {
			throw new IllegalArgumentException();
		}
		
		dsl
			.deleteFrom(CARDS)
			.where(CARDS.DECK_ID.eq(deckId)
					.and(CARDS.ID.eq(cardId)))
			.execute();
	}
	
	public void deleteCards(
			int deckId,
			int cardId,
			List<Integer> ids) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
		if (cardId <= 0) {
			throw new IllegalArgumentException();
		}
		if (ids == null
				|| ids.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		dsl
			.deleteFrom(CARDS)
			.where(CARDS.DECK_ID.eq(deckId)
					.and(CARDS.ID.in(ids)))
			.execute();
	}
	
	@Transactional(readOnly = true)
	public Optional<Card> getCard(
			int deckId,
			int cardId) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
		if (cardId <= 0) {
			throw new IllegalArgumentException();
		}
		
		CardsRecord r = dsl
				.fetchAny(
						CARDS,
						CARDS.DECK_ID.eq(deckId)
						.and(CARDS.ID.eq(cardId)));
		if (r == null) {
			return Optional.empty();
		} else {
			return Optional.of(r.into(Card.class));
		}
	}
	
	@Transactional(readOnly = true)
	public List<Card> getCards(
			int deckId,
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
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
				.selectFrom(CARDS)
				.where(CARDS.DECK_ID.eq(deckId)
						.or(CARDS.SIDE_A.like("%" + descriptionFilter + "%"))
						.or(CARDS.SIDE_B.like("%" + descriptionFilter + "%"))
						.or(CARDS.NOTES.like("%" + descriptionFilter + "%"))
						.or(CARDS.TAGS.like("%" + descriptionFilter + "%")))
				.orderBy(
						CARDS.TAGS,
						CARDS.SIDE_A,
						CARDS.SIDE_B,
						CARDS.ID)
				.limit(maxResults)
				.offset(firstResult)
				.fetch().into(Card.class);
	}
	
	@Transactional(readOnly = true)
	public List<CardSummary> getCardSummaries(
			int deckId,
			String descriptionFilter,
			int firstResult,
			int maxResults) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
		if (descriptionFilter == null) {
			throw new IllegalArgumentException();
		}
		if (firstResult < 0) {
			throw new IllegalArgumentException();
		}
		if (maxResults <= 0) {
			throw new IllegalArgumentException();
		}
		
		Table<Record2<Integer, OffsetDateTime>> planTable = dsl
				.select(
						CARDS_INSTANCES.CARD_ID.as("cardId"),
						DSL.max(CARDS_PLANS.LAST_DATE).as("lastDate"))
				.from(CARDS_INSTANCES)
				.leftOuterJoin(CARDS_PLANS)
				.on(CARDS_INSTANCES.ID.eq(CARDS_PLANS.CARD_INSTANCE_ID))
				.groupBy(CARDS_INSTANCES.CARD_ID)
				.asTable("planTable");
		
		return dsl
				.select(
						CARDS.ID.as("cardId"),
						CARDS.SIDE_A.as("sideA"),
						CARDS.SIDE_B.as("sideB"),
						CARDS.NOTES.as("notes"),
						CARDS.TAGS.as("tags"),
						planTable.field("lastDate").as("lastDate"))
				.from(CARDS)
				.leftOuterJoin(planTable)
				.on(CARDS.ID.eq(planTable.field("cardId", Integer.class)))
				.where(CARDS.DECK_ID.eq(deckId)
						.and(
								(CARDS.SIDE_A.like("%" + descriptionFilter + "%"))
								.or(CARDS.SIDE_B.like("%" + descriptionFilter + "%"))
								.or(CARDS.NOTES.like("%" + descriptionFilter + "%"))
								.or(CARDS.TAGS.like("%" + descriptionFilter + "%"))))
				.orderBy(
						CARDS.TAGS,
						CARDS.SIDE_A,
						CARDS.SIDE_B,
						CARDS.ID)
				.limit(maxResults)
				.offset(firstResult)
				.fetch().into(CardSummary.class);
	}
}
