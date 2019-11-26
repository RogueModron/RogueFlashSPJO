package app.daos;

import static app.db.tables.CardsReviews.CARDS_REVIEWS;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.db.tables.records.CardsReviewsRecord;
import app.model.CardReview;

@Repository
@Transactional
public class CardReviewsDao {
	@Autowired
	private DSLContext dsl;
	
	public CardReview createCardReview(CardReview cardReview) {
		if (cardReview == null) {
			throw new IllegalArgumentException();
		}
		
		CardsReviewsRecord r = dsl.newRecord(CARDS_REVIEWS, cardReview);
		r.insert();
		
		return r.into(CardReview.class);
	}
}
