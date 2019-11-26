package app;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class Application {
	public static void main(String[] args) {
		/*
		 * Remember:
		 *           --debug 
		 */
		
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(300);
		executor.setThreadNamePrefix("RogueFlash-");
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.initialize();
		return executor;
	}
}
