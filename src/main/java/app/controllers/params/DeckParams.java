package app.controllers.params;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import app.model.Deck;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class DeckParams implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@NotNull
	private String description = "";
	
	@Getter
	@Setter
	@NotNull
	private String notes = "";
	
	public Deck buildDeck() {
		return Deck.builder()
				.description(description)
				.notes(notes)
				.build();
	}
	
	public Deck mergeDeck(Deck deck) {
		return new Deck(
				deck.getId(),
				deck.getVersion(),
				description,
				notes);
	}
}
