package app.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import app.model.Deck;

@Component
public class DecksBatch {
	@Autowired
	private JobBuilderFactory jbf;
	
	@Autowired
	private StepBuilderFactory sbf;

	@Bean
	public Job decksJob(Step decksStep) {
		return jbf.get("testJobOnDecks")
				.start(decksStep)
				.build();
	}
	
	@Bean
	protected Step decksStep(
			DeckItemReader reader,
			DeckItemProcessor processor,
			DeckItemWriter writer) {
		return sbf.get("stepForDecks")
			.<Deck, DeckNotes> chunk(3)
			.reader(reader)
			.processor(processor)
			.writer(writer)
			.allowStartIfComplete(true)
			.build();
	}
}
