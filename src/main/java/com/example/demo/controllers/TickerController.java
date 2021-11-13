package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Ticker;
import com.example.demo.services.TickerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController

@RequestMapping(path = "api/v1")
public class TickerController {


	private static Logger asynclogger = LogManager.getLogger(TickerController.class);
	private TickerService tickerService;

	public TickerController(TickerService tickerService) {
		super();
		this.tickerService = tickerService;
	}

	@GetMapping("/ticker")
	public ResponseEntity<List<Ticker>> getAllTickers()
	{
		asynclogger.info("Get All Tickers Service initiated");

		try
		{
			List<Ticker> tickers=tickerService.getAllTickers();
			if (tickers.size()==0)
			{
				asynclogger.error("No Records Found");
				return new ResponseEntity <>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return ResponseEntity.of(Optional.of(tickers));
			}
		}
		catch(Exception e)
		{
			asynclogger.error("Error Occured:"+e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/ticker/{tickerId}")
	public ResponseEntity <Ticker> getTickerById(@Valid @PathVariable String tickerId)
	{
		//System.out.println("PathVariable:"+tickerId);

		asynclogger.info("Get Ticker Service initiated by Id");
		Ticker ticker = null;
		try
		{
			ticker=tickerService.getTickerById(Long.parseLong(tickerId));
			//System.out.println("GETticker:"+ticker);
			return ResponseEntity.of(Optional.of(ticker));

		}

		catch(NoSuchElementException e)
		{
			asynclogger.error("Not found with Stacktrace:"+e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		catch(Exception e)
		{
			asynclogger.error("Error Occured:"+e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/ticker")
	public ResponseEntity<Ticker> updateTickerById(@Valid @RequestBody Ticker ticker)
	{
		asynclogger.info("Update Ticker Service initiated");
		Ticker ticker1 = null;
		try
		{
			ticker1=tickerService.getTickerById((long) ticker.getId());
			//System.out.println("GETticker:"+ticker);
			tickerService.updateTicker(ticker);
			return ResponseEntity.of(Optional.of(ticker));

		}

		catch(NoSuchElementException e)
		{
			asynclogger.error("Error Occured:"+e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/ticker/{tickerId}")
	public ResponseEntity<?> deleteTickerById(@Valid @PathVariable String tickerId)
	{

		asynclogger.info("Delete Ticker by Id Service initiated");
		try
		{
			System.out.println("PathVariable:"+tickerId);
			tickerService.deleteTicker(Long.parseLong(tickerId));
			return new ResponseEntity <>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			asynclogger.error("Ticker Not found, "+e);
			//e.printStackTrace();
			return new ResponseEntity <>(HttpStatus.INTERNAL_SERVER_ERROR);
			// new ResponseStatusException(HttpStatus., "Cause description here");
		}
	}

	@DeleteMapping("/ticker")
	public ResponseEntity<?> deleteAllTickers()
	{
		asynclogger.info("Delete all Tickers Service initiated");
		try
		{
			tickerService.deleteAllTickers();
			return new ResponseEntity <>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			asynclogger.error("Ticker Not found, "+e);
			//e.printStackTrace();
			return new ResponseEntity <>(HttpStatus.INTERNAL_SERVER_ERROR);
			// new ResponseStatusException(HttpStatus., "Cause description here");
		}
	}

	@GetMapping(value = "/ticker/blogPageable")
	Page blogPageable(Pageable pageable) {
		return tickerService.getAllTickersWithPage(pageable);
	}
}
