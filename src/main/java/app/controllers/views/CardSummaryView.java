package app.controllers.views;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import app.model.views.CardSummary;
import lombok.Builder;
import lombok.Getter;

@Builder
public class CardSummaryView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private int cardId;
	
	@Builder.Default
	@Getter
	private String sideA = "";
	
	@Builder.Default
	@Getter
	private String sideB = "";
	
	@Builder.Default
	@Getter
	private String notes = "";
	
	@Builder.Default
	@Getter
	private String tags = "";
	
	@Getter
	private OffsetDateTime lastDate;
	
	public static CardSummaryView buildFromCardSummary(CardSummary cardSummary) {
		return CardSummaryView.builder()
				.cardId(cardSummary.getCardId())
				.sideA(cardSummary.getSideA())
				.sideB(cardSummary.getSideB())
				.notes(cardSummary.getNotes())
				.tags(cardSummary.getTags())
				.lastDate(cardSummary.getLastDate())
				.build();
	}
	
	public static List<CardSummaryView> buildFromCardSummaries(List<CardSummary> cardSummaries) {
		List<CardSummaryView> views = new ArrayList<>();
		for (CardSummary cardSummary : cardSummaries) {
			views.add(CardSummaryView.buildFromCardSummary(cardSummary));
		}
		return views;
	}
}
