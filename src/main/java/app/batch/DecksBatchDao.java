package app.batch;

import static app.db.tables.Decks.DECKS;

import java.util.List;

import org.jooq.BatchBindStep;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.db.tables.records.DecksRecord;
import app.model.Deck;

@Repository
@Transactional
public class DecksBatchDao {
	@Autowired
	private DSLContext dsl;
	
	@Transactional(readOnly = true)
	public List<Integer> getIds() {
		return dsl
				.selectFrom(DECKS)
				.orderBy(DECKS.ID)
				.fetch(DECKS.ID);
	}
	
	@Transactional(readOnly = true)
	public Deck getDeck(int id) {
		DecksRecord r = dsl
				.fetchAny(
						DECKS,
						DECKS.ID.eq(id));
		return r.into(Deck.class);
	}
	
	public void batchUpdateNotes(List<? extends DeckNotes> decksNotes) {
		if (decksNotes.size() <= 0) {
			return;
		}
		
		String query = dsl
				.update(DECKS)
				.set(DECKS.NOTES, "")
				.where(DECKS.ID.eq(0))
				.getSQL();
		BatchBindStep batch = dsl.batch(query);
		for (DeckNotes deckNotes : decksNotes) {
			batch.bind(
					deckNotes.getNotes(),
					deckNotes.getId());
		}
		batch.execute();
	}
}
