package app.services;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.daos.CardInstancesDao;
import app.daos.CardPlansDao;
import app.daos.CardReviewsDao;
import app.daos.CardsDao;
import app.daos.CurrentReviewsDao;
import app.model.Card;
import app.model.CardInstance;
import app.model.CardPlan;
import app.model.CardReview;
import app.model.CurrentReview;
import app.model.planner.Planner;
import app.model.planner.PlannerResult;
import app.model.planner.ReviewValues;
import app.model.views.CurrentReviewDetail;

@Service
@Transactional
public class CurrentReviewsService {
	@Autowired
	private CardsDao cardsDao;
	
	@Autowired
	private CardInstancesDao cardInstancesDao;
	
	@Autowired
	private CardPlansDao cardPlansDao;
	
	@Autowired
	private CardReviewsDao cardReviewsDao;
	
	@Autowired
	private CurrentReviewsDao currentReviewsDao;
	
	
	public Optional<CurrentReviewDetail> initCurrentReview(int deckId) {
		currentReviewsDao.deleteCurrentReview(deckId);
		
		Optional<CardInstance> optionalCardInstance = cardInstancesDao.getNextCardInstanceToReview(
				deckId,
				OffsetDateTime.now());
		if (!optionalCardInstance.isPresent()) {
			return Optional.empty();
		}
		
		CardInstance cardInstance = optionalCardInstance.get();
		
		Optional<Card> optionalCard = cardsDao.getCard(
				deckId,
				cardInstance.getCardId());
		if (!optionalCard.isPresent()) {
			return Optional.empty();
		}
		
		Card card = optionalCard.get();
		
		CurrentReview currentReview = CurrentReview.builder()
				.deckId(deckId)
				.cardInstanceId(cardInstance.getId())
				.lastDateTime(OffsetDateTime.now())
				.build();
		currentReview = currentReviewsDao.createCurrentReview(currentReview);
		
		String sideA = card.getSideA();
		String sideB = card.getSideB();
		if (!cardInstance.isSideAToB()) {
			sideA = card.getSideB();
			sideB = card.getSideA();
		}
		
		CurrentReviewDetail currentReviewDetail = new CurrentReviewDetail(
				currentReview.getId(),
				sideA,
				sideB,
				card.getNotes());
		return Optional.of(currentReviewDetail);
	}
	
	public Optional<CurrentReview> valueCurrentReview(
			int deckId,
			int currentReviewId,
			ReviewValues value) {
		Optional<CurrentReview> optionalCurrentReview = currentReviewsDao.getCurrentReview(
				deckId,
				currentReviewId);
		if (!optionalCurrentReview.isPresent()) {
			return Optional.empty();
		}
		
		valueCardInstance(
				optionalCurrentReview.get().getCardInstanceId(),
				value);
		
		currentReviewsDao.deleteCurrentReview(currentReviewId);
		
		return optionalCurrentReview;
	}
	
	
	private void valueCardInstance(
			int id,
			ReviewValues value) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		
		Optional<CardInstance> optionalCardInstance = cardInstancesDao.getCardInstance(id);
		
		if (!optionalCardInstance.isPresent()) {
			throw new RuntimeException("CardInstance not found.");
		}
		
		Optional<CardPlan> optionalCardPlan = cardPlansDao.getCardPlanByCardInstanceId(optionalCardInstance.get().getId());
		
		if (!optionalCardPlan.isPresent()) {
			throw new RuntimeException("CardPlan not found.");
		}
		
		CardPlan cardPlan = optionalCardPlan.get();
		
		OffsetDateTime now = OffsetDateTime.now();
		
		if (cardPlan.getNextDate() != null &&
				cardPlan.getNextDate().isAfter(now))
		{
			throw new RuntimeException("NextDate is after Now.");
		}
		
		PlannerResult plannerResult = Planner.planNext(
				value,
				now,
				cardPlan.getLastDate());
		
		cardPlan = new CardPlan(
				cardPlan.getId(),
				cardPlan.getCardInstanceId(),
				plannerResult.getNextDate(),
				plannerResult.getNextDays(),
				now,
				plannerResult.getPassedDays());
		
		CardReview cardReview = CardReview.builder()
				.cardInstanceId(cardPlan.getCardInstanceId())
				.dateTime(now)
				.value(value)
				.build();
		
		cardPlansDao.updateCardPlan(cardPlan);
		cardReviewsDao.createCardReview(cardReview);
	}
}
