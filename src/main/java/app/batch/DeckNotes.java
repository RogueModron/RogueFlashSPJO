package app.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DeckNotes {
	@Getter
	private int id;
	
	@Getter
	private String notes;
}
