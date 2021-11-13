package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Ticker;
import com.example.demo.scheduler.ConsumeService;

public class schedulerTestMain {

	private static ConsumeService consumeService;

	@Autowired
	public schedulerTestMain(ConsumeService consumeService) {
		//super();
		this.consumeService = consumeService;
	}
	public static void main(String[] args)
	{
		try
		{
			Ticker t2=consumeService.getTicker();
			System.out.println(t2.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}

}
