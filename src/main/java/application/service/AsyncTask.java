package application.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask 
{
	static int count=1;
	
	@Async("taskExecutor")
	public void task(long breakTime)throws InterruptedException
	{
		Thread.sleep(breakTime);
		String message = count+++""+Thread.currentThread() + "Task-End for long Task in " + (breakTime / 1000);
		System.out.println(message);		
	}
}
