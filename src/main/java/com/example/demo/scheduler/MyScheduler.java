package com.example.demo.scheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.controllers.TickerController;
import com.example.demo.entities.Ticker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Component
public class MyScheduler {

	private static Logger asynclogger = LogManager.getLogger(TickerController.class);
	private ConsumeService consumeService;

	@Autowired
	public MyScheduler(ConsumeService consumeService) {
		//super();
		this.consumeService = consumeService;
	}

	@Scheduled(fixedDelay = 5000, initialDelay = 1000)// Executes the task for every 5 seconds 
	//after 3 seconds from the application startup
	//Scheduled(cron="*/5 * * * * ?")
	public void schedule() {
		try
		{
			Ticker t1=consumeService.getTicker();
			consumeService.addTicker(t1);
		} 
		catch (Exception e) 
		{
			asynclogger.error("Error Occured: "+e);
			e.printStackTrace();
		} 
	}

}