package app.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.model.Deck;

@Component
@StepScope
public class DeckItemReader implements ItemReader<Deck> {
	@Autowired
	private DecksBatchDao decksBatchDao;
	
	private List<Integer> ids;
	private int index;
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		ids = decksBatchDao.getIds();
		index = 0;
		
		System.out.println("DeckItemReader - items: " + ids.size());
	}
	
	@Override
	public Deck read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Read Deck - index: " + index);
		
		if (index >= ids.size()) {
			return null;
		}
		
		Deck deck = decksBatchDao.getDeck(ids.get(index));
		index++;
		return deck;
	}
}
