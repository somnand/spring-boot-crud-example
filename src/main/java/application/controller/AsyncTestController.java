package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.service.AsyncTestService;

/**
 * This class is the Controller class which takes in the GET request call
 * and fires two service methods(viz: longTask() and shortTask().
 * 
 * These should work asynchronously and thus response will be returned instantly.
 * Short task will be executed in 2s and Long task will take 10s to run and show the output in console
 * 
 * The thread composition follows the configuration as per AsyncConfig class.
 * 
 * As a second process service is fed with N=50 threads and called. 
 *  
 * @author SomsuryaNanda
 * 
 */
@RestController
public class AsyncTestController 
{
	@Autowired
    private AsyncTestService service;
		
	@RequestMapping("/asyncTest")
	public String testAsyncCall()throws InterruptedException
	{
		System.out.println("Starting...");
		//service.longTask(10000);//10s
		//service.shortTask(2000);//2s 
		service.callMultipleThreads(50);
		System.out.println("Process Finished!");
		
		return "Process Finished!"+Thread.currentThread();
	}	
	
}
