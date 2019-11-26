package app.controllers.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.model.Card;
import lombok.Builder;
import lombok.Getter;

@Builder
public class CardView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter
	private Integer deckId;
	
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
	
	public static CardView buildFromCard(Card card) {
		return CardView.builder()
				.id(card.getId())
				.deckId(card.getDeckId())
				.sideA(card.getSideA())
				.sideB(card.getSideB())
				.notes(card.getNotes())
				.tags(card.getTags())
				.build();
	}
	
	public static Optional<CardView> buildFromCard(Optional<Card> optionalCard) {
		if (optionalCard.isPresent()) {
			return Optional.of(buildFromCard(optionalCard.get()));
		} else {
			return Optional.empty();
		}
	}
	
	public static List<CardView> buildFromCards(List<Card> cards) {
		List<CardView> views = new ArrayList<>();
		for (Card card : cards) {
			views.add(CardView.buildFromCard(card));
		}
		return views;
	}
}
