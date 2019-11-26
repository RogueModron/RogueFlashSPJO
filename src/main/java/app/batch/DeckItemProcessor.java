package app.batch;

import java.time.OffsetDateTime;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import app.model.Deck;

@Component
@StepScope
public class DeckItemProcessor implements ItemProcessor<Deck, DeckNotes> {
	@Override
	public DeckNotes process(Deck item) throws Exception {
		DeckNotes deckNotes = new DeckNotes(
				item.getId(),
				"Batch: " + OffsetDateTime.now().toString());
		return deckNotes;
	}
}
