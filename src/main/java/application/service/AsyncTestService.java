package application.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Component //Can be used as a Component too
public class AsyncTestService 
{
	@Autowired
	AsyncTask executor;
	//@Async("taskExecutor")
	public void longTask(long breakTime) throws InterruptedException 
	{
		Thread.sleep(breakTime);
		String message = Thread.currentThread() + "Task-End for long Task in " + (breakTime / 1000);
		System.out.println(message);
		//return new CompletableFuture<>().;
	}

	//@Async("taskExecutor")
	public void shortTask(long breakTime) throws InterruptedException 
	{
		Thread.sleep(breakTime);
		System.out.println(Thread.currentThread() + "Task-End for short Task in " + (breakTime / 1000));
	}
	
	public void callMultipleThreads(int numThreads)throws InterruptedException
	{
		Random r = new Random();
		for(int i=0;i<numThreads;i++)
		{
			executor.task(r.nextInt(10*1000));//To simulate a long time or short time running thread		
		}		
	}
	
}
