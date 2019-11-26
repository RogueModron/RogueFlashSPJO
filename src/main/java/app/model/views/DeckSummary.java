package app.model.views;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DeckSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private int deckId = 0;
	
	@Getter
	private String description = "";
	
	@Getter
	private String notes = "";
	
	@Getter
	private int numberOfSides = 0;
}
