package app.batch;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class DeckItemWriter implements ItemWriter<DeckNotes> {
	@Autowired
	private DecksBatchDao decksBatchDao;
	
	@Override
	public void write(List<? extends DeckNotes> items) throws Exception {
		decksBatchDao.batchUpdateNotes(items);
	}
}
