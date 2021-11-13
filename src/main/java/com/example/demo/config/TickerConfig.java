package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dao.TickerRepository;
import com.example.demo.entities.Ticker;

@Configuration
public class TickerConfig {
	@Bean
	CommandLineRunner commandlineRunner(TickerRepository ticketrepository) {
		
		return args->{
			Ticker t1=new Ticker (1,1,1,1,1,1,1,1,1,1,1);
			ticketrepository.save(t1);
		};
		
		
	}
	

}
