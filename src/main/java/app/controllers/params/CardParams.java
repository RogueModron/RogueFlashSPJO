package app.controllers.params;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import app.model.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CardParams implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@NotNull
	private String sideA = "";
	
	@Getter
	@Setter
	@NotNull
	private String sideB = "";
	
	@Getter
	@Setter
	@NotNull
	private String notes = "";
	
	@Getter
	@Setter
	@NotNull
	private String tags = "";
	
	public Card buildCard(int deckId) {
		return Card.builder()
				.deckId(deckId)
				.notes(notes)
				.sideA(sideA)
				.sideB(sideB)
				.tags(tags)
				.build();
	}
	
	public Card mergeCard(Card card) {
		return new Card(
				card.getId(),
				card.getVersion(),
				card.getDeckId(),
				sideA,
				sideB,
				notes,
				tags);
	}
}
