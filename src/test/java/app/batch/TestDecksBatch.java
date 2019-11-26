package app.batch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBatchTest
public class TestDecksBatch {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private Job decksJob;
	
	@Test
	public void testJob() throws Exception {
		jobLauncherTestUtils.setJob(decksJob);
		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
}
