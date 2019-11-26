package app.controllers.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.model.Deck;
import lombok.Builder;
import lombok.Getter;

@Builder
public class DeckView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Builder.Default
	@Getter
	private String description = "";
	
	@Builder.Default
	@Getter
	private String notes = "";
	
	public static DeckView buildFromDeck(Deck deck) {
		return DeckView.builder()
				.id(deck.getId())
				.description(deck.getDescription())
				.notes(deck.getNotes())
				.build();
	}
	
	public static Optional<DeckView> buildFromDeck(Optional<Deck> optionalDeck) {
		if (optionalDeck.isPresent()) {
			return Optional.of(buildFromDeck(optionalDeck.get()));
		} else {
			return Optional.empty();
		}
	}
	
	public static List<DeckView> buildFromDecks(List<Deck> decks) {
		List<DeckView> views = new ArrayList<>();
		for (Deck deck : decks) {
			views.add(DeckView.buildFromDeck(deck));
		}
		return views;
	}
}
