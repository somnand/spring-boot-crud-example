package security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	/**
	 * To recreate java.util.concurrent.RejectedExecutionException reduce the numbers such that 
	 * No. of Tasks > Max Pool + Queue Capacity 
	 * eg : 50 > 46 + 3 
	 * @return
	 */
	 @Bean("taskExecutor")
	 public ThreadPoolTaskExecutor taskExecutor() 
	 {
		 ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		 executor.setCorePoolSize(2);
		 executor.setMaxPoolSize(3);//The sum of MaxPool Size and Queue Size must be equals to or more than the number of tasks submitted.
		 executor.setQueueCapacity(47);//47+3 = 50 ie. 50 tasks submitted in AsyncTestService.callMultipleThreads() 
		 executor.setThreadNamePrefix("MyAsyncThread-");
		 executor.initialize();
		 
		 return executor;
	 }

}
