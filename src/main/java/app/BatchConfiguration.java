package app;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Bean
	public BatchConfigurer batchConfigurer() {
		return new DefaultBatchConfigurer() {
			@Override
			public void setDataSource(DataSource dataSource) {
				// Automatically use in-memory data source:
				//     https://docs.spring.io/spring-batch/4.1.x/reference/html/job.html#configuringJobRepository
			}
		};
	}
	
	@Bean(name = "asyncJobLauncher")
	public JobLauncher asyncJobLauncher(JobRepository jobRepository) throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
}
