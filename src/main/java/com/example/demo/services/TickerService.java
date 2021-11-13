package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.dao.TickerRepository;
import com.example.demo.entities.Ticker;

@Service
public class TickerService {
	
	private final TickerRepository tickerRepository;
	
	@Autowired
	public TickerService(TickerRepository tickerRepository) {
		super();
		this.tickerRepository = tickerRepository;
	}

	public List<Ticker> getAllTickers() 
	{
		return tickerRepository.findAll();
	}

	public Ticker getTickerById(long tickerId)
	{
        return tickerRepository.findById(tickerId).get();
       
		
	}

	public Ticker updateTicker(Ticker ticker)
	{
	     
		tickerRepository.save(ticker);
		return ticker;
	}

	public void deleteTicker(long tickerId)
	{
		Ticker t2=tickerRepository.getOne(tickerId);
		//System.out.println(t2);
		tickerRepository.delete(t2);;
	}

	public void deleteAllTickers() 
	{
		tickerRepository.deleteAllInBatch();;
		
	}
	
	public Page getAllTickersWithPage(Pageable pageable) 
	{
		return tickerRepository.findAll(pageable);
		
	}

	

}
