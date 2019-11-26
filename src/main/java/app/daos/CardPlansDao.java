package app.daos;

import static app.db.tables.CardsPlans.CARDS_PLANS;

import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.db.tables.records.CardsPlansRecord;
import app.model.CardPlan;

@Repository
@Transactional
public class CardPlansDao {
	@Autowired
	private DSLContext dsl;
	
	public CardPlan createCardPlan(CardPlan cardPlan) {
		if (cardPlan == null) {
			throw new IllegalArgumentException();
		}
		
		CardsPlansRecord r = dsl.newRecord(CARDS_PLANS, cardPlan);
		r.insert();
		
		return r.into(CardPlan.class);
	}
	
	public CardPlan updateCardPlan(CardPlan cardPlan) {
		if (cardPlan == null) {
			throw new IllegalArgumentException();
		}
		
		CardsPlansRecord r = dsl.newRecord(CARDS_PLANS, cardPlan);
		r.update();
		
		return r.into(CardPlan.class);
	}
	
	@Transactional(readOnly = true)
	public Optional<CardPlan> getCardPlanByCardInstanceId(int cardInstanceId) {
		if (cardInstanceId <= 0) {
			throw new IllegalArgumentException();
		}
		
		CardsPlansRecord r = dsl
				.fetchAny(
						CARDS_PLANS,
						CARDS_PLANS.CARD_INSTANCE_ID.eq(cardInstanceId));
		if (r == null) {
			return Optional.empty();
		} else {
			return Optional.of(r.into(CardPlan.class));
		}
	}
}
