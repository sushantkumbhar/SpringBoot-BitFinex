package com.example.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controllers.TickerController;
import com.example.demo.dao.TickerRepository;
import com.example.demo.entities.Ticker;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;  

@Service
public class ConsumeService {

	private static Logger asynclogger = LogManager.getLogger(TickerController.class);
	private final TickerRepository tickerrepositoy;
	@Value("${ticker.endpoint}")
	private String endpoint;
	
	@Autowired
	public ConsumeService(TickerRepository tickerrepositoy) {
		super();
		this.tickerrepositoy = tickerrepositoy;
	}

	public Ticker getTicker() throws Exception
	{

		//String endpoint="https://api-pub.bitfinex.com/v2/ticker/tBTCUSD";
		//System.out.println("Connecting to Endpoint:"+endpoint);
		asynclogger.info("Connecting to Endpoint:"+endpoint);
		RestTemplate rt=new RestTemplate();
		//Object[] T=(Object[]) rt.getForObject("https://api-pub.bitfinex.com/v2/ticker/tBTCUSD", Object.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		String ticker = rt.exchange(endpoint, HttpMethod.GET, entity, String.class).getBody();
        //Response from the Service is Json Array for the Object Mapper we need to create a
		//JSON Object
	    //Object ticker = rt.exchange(endpoint, HttpMethod.GET, entity, Object.class).getBody();

		Gson googleJson = new Gson();
		ArrayList javaArrayListFromGSON = googleJson.fromJson(ticker, ArrayList.class);
		JSONObject jo = new JSONObject();
		jo.put("bid", javaArrayListFromGSON.get(0));
		jo.put("bidSize", javaArrayListFromGSON.get(1));
		jo.put("ask", javaArrayListFromGSON.get(2));
		jo.put("askSize", javaArrayListFromGSON.get(3));
		jo.put("dailyChange", javaArrayListFromGSON.get(4));
		jo.put("dailyChangeRelative", javaArrayListFromGSON.get(5));
		jo.put("lastPrice", javaArrayListFromGSON.get(6));
		jo.put("volume", javaArrayListFromGSON.get(7));
		jo.put("high", javaArrayListFromGSON.get(8));
		jo.put("low", javaArrayListFromGSON.get(9));
		
		//System.out.println(jo);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		Ticker t1 = mapper.readValue(jo.toString(), Ticker.class);

		return t1;

	}
	
	public void addTicker(Ticker t) 
	{
		tickerrepositoy.save(t);
	}
	
	
}
