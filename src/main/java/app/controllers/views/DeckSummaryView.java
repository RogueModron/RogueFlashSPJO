package app.controllers.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.model.views.DeckSummary;
import lombok.Builder;
import lombok.Getter;

@Builder
public class DeckSummaryView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private int deckId;
	
	@Builder.Default
	@Getter
	private String description = "";
	
	@Builder.Default
	@Getter
	private String notes = "";
	
	@Getter
	private int numberOfSides;
	
	public static DeckSummaryView buildFromDeckSummary(DeckSummary deckSummary) {
		return DeckSummaryView.builder()
				.deckId(deckSummary.getDeckId())
				.description(deckSummary.getDescription())
				.notes(deckSummary.getNotes())
				.numberOfSides(deckSummary.getNumberOfSides())
				.build();
	}
	
	public static List<DeckSummaryView> buildFromDeckSummaries(List<DeckSummary> deckSummaries) {
		List<DeckSummaryView> views = new ArrayList<>();
		for (DeckSummary deckSummary : deckSummaries) {
			views.add(DeckSummaryView.buildFromDeckSummary(deckSummary));
		}
		return views;
	}
}
