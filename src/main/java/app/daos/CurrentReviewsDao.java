package app.daos;

import static app.db.tables.CurrentReviews.CURRENT_REVIEWS;

import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.db.tables.records.CurrentReviewsRecord;
import app.model.CurrentReview;

@Repository
@Transactional
public class CurrentReviewsDao {
	@Autowired
	private DSLContext dsl;
	
	public CurrentReview createCurrentReview(CurrentReview currentReview) {
		if (currentReview == null) {
			throw new IllegalArgumentException();
		}
		
		CurrentReviewsRecord r = dsl.newRecord(CURRENT_REVIEWS, currentReview);
		r.insert();
		
		return r.into(CurrentReview.class);
	}
	
	public CurrentReview updateCurrentReview(CurrentReview currentReview) {
		if (currentReview == null) {
			throw new IllegalArgumentException();
		}
		
		CurrentReviewsRecord r = dsl.newRecord(CURRENT_REVIEWS, currentReview);
		r.update();
		
		return null;
	}
	
	public void deleteCurrentReview(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		
		dsl
			.deleteFrom(CURRENT_REVIEWS)
			.where(CURRENT_REVIEWS.ID.eq(id))
			.execute();
	}
	
	@Transactional(readOnly = true)
	public Optional<CurrentReview> getCurrentReview(
			int deckId,
			int currentReviewId) {
		if (deckId <= 0) {
			throw new IllegalArgumentException();
		}
		if (currentReviewId <= 0) {
			throw new IllegalArgumentException();
		}
		
		CurrentReviewsRecord r = dsl
				.fetchAny(
						CURRENT_REVIEWS,
						CURRENT_REVIEWS.DECK_ID.eq(deckId)
						.and(CURRENT_REVIEWS.ID.eq(currentReviewId)));
		if (r == null) {
			return Optional.empty();
		} else {
			return Optional.of(r.into(CurrentReview.class));
		}
	}
}
