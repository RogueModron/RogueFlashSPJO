package app.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
	@Autowired
	private JobLauncher asyncJobLauncher;
	
	@Autowired
	private Job decksJob;
	
	@GetMapping("/decks/batch")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void launchDecksBatch() throws Exception {
		asyncJobLauncher.run(
				decksJob,
				new JobParameters());
	}
}
