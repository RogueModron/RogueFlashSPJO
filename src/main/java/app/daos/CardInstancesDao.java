package app.daos;

import static app.db.tables.Cards.CARDS;
import static app.db.tables.CardsInstances.CARDS_INSTANCES;
import static app.db.tables.CardsPlans.CARDS_PLANS;
import static app.db.tables.Decks.DECKS;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.db.tables.records.CardsInstancesRecord;
import app.model.CardInstance;

@Repository
@Transactional
public class CardInstancesDao {
	@Autowired
	private DSLContext dsl;
	
	public CardInstance createCardInstance(CardInstance cardInstance) {
		if (cardInstance == null) {
			throw new IllegalArgumentException();
		}
		
		CardsInstancesRecord r = dsl.newRecord(CARDS_INSTANCES, cardInstance);
		r.insert();
		
		return r.into(CardInstance.class);
	}
	
	public CardInstance updateCardInstance(CardInstance cardInstance) {
		if (cardInstance == null) {
			throw new IllegalArgumentException();
		}
		
		CardsInstancesRecord r = dsl.newRecord(CARDS_INSTANCES, cardInstance);
		r.update();
		
		return r.into(CardInstance.class);
	}
	
	@Transactional(readOnly = true)
	public Optional<CardInstance> getCardInstance(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		
		CardsInstancesRecord r = dsl
				.fetchAny(
						CARDS_INSTANCES,
						CARDS_INSTANCES.CARD_ID.eq(id));
		if (r == null) {
			return Optional.empty();
		} else {
			return Optional.of(r.into(CardInstance.class));
		}
	}
	
	@Transactional(readOnly = true)
	public List<CardInstance> getCardsInstancesByCardId(int cardId) {
		if (cardId <= 0) {
			throw new IllegalArgumentException();
		}
		
		return dsl
				.selectFrom(CARDS_INSTANCES)
				.where(CARDS_INSTANCES.CARD_ID.eq(cardId))
				.fetch().into(CardInstance.class);
	}
	
	@Transactional(readOnly = true)
	public int countCardsInstancesToReview(
			int deckId,
			OffsetDateTime dateLimit) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
		if (dateLimit == null) {
			throw new IllegalArgumentException();
		}
		
		return dsl
				.select(DSL.count().as("c"))
				.from(DECKS)
				.innerJoin(CARDS)
				.on(DECKS.ID.eq(CARDS.DECK_ID))
				.innerJoin(CARDS_INSTANCES)
				.on(CARDS.ID.eq(CARDS_INSTANCES.CARD_ID))
				.innerJoin(CARDS_PLANS)
				.on(CARDS_INSTANCES.ID.eq(CARDS_PLANS.CARD_INSTANCE_ID))
				.where(
						DECKS.ID.eq(deckId)
						.and(
								CARDS_PLANS.NEXT_DATE.isNull()
								.or(CARDS_PLANS.NEXT_DATE.le(dateLimit))
							)
						.and(CARDS.SIDE_A.notEqual(""))
						.and(CARDS.SIDE_B.notEqual(""))
						.and(CARDS_INSTANCES.DISABLED.isFalse()))
				.fetchOne("c", int.class);
	}
	
	@Transactional(readOnly = true)
	public Optional<CardInstance> getNextCardInstanceToReview(
			int deckId,
			OffsetDateTime dateLimit) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
		if (dateLimit == null) {
			throw new IllegalArgumentException();
		}
		
		Record r = dsl
				.select(CARDS_INSTANCES.asterisk())
				.from(DECKS)
				.innerJoin(CARDS)
				.on(DECKS.ID.eq(CARDS.DECK_ID))
				.innerJoin(CARDS_INSTANCES)
				.on(CARDS.ID.eq(CARDS_INSTANCES.CARD_ID))
				.innerJoin(CARDS_PLANS)
				.on(CARDS_INSTANCES.ID.eq(CARDS_PLANS.CARD_INSTANCE_ID))
				.where(
						DECKS.ID.eq(deckId)
						.and(
								CARDS_PLANS.NEXT_DATE.isNull()
								.or(CARDS_PLANS.NEXT_DATE.le(dateLimit))
							)
						.and(CARDS.SIDE_A.notEqual(""))
						.and(CARDS.SIDE_B.notEqual(""))
						.and(CARDS_INSTANCES.DISABLED.isFalse()))
				.orderBy(CARDS_PLANS.NEXT_DATE)
				.fetchAny();
		if (r == null) {
			return Optional.empty();
		} else {
			return Optional.of(r.into(CardInstance.class));
		}
	}
}
