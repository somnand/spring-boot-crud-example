package application.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
//@Component //Can be used as a Component too
public class AsyncTestService 
{	
	@Async("taskExecutor")
	public void longTask(long breakTime) throws InterruptedException 
	{
		Thread.sleep(breakTime);
		System.out.println(Thread.currentThread() + "Task-End for long Task in " + (breakTime / 1000));
	}

	@Async("taskExecutor")
	public void shortTask(long breakTime) throws InterruptedException 
	{
		Thread.sleep(breakTime);
		System.out.println(Thread.currentThread() + "Task-End for short Task in " + (breakTime / 1000));
	}
	
}
