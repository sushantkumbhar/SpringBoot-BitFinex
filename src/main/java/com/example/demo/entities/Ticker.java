package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
@Table

public class Ticker {

	@Id
	@SequenceGenerator(name="ticker_sequence", 
	sequenceName = "ticker_sequence", allocationSize = 1)

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticker_sequence")
	
	private long id;
	@NotNull(message = "Please provide bid")
	private float bid;
	private float bidSize;
	private float ask;
	private float askSize;
	private float dailyChange;
	private float dailyChangeRelative;
	private float lastPrice;
	private float volume;
	private float high;
	private float low;
	
	public float getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	public float getBid() {
		return bid;
	}
	
	public void setBid(float bid) {
		this.bid = bid;
	}
	public float getBidSize() {
		return bidSize;
	}
	public void setBidSize(float bidSize) {
		this.bidSize = bidSize;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getAskSize() {
		return askSize;
	}
	public void setAskSize(float askSize) {
		this.askSize = askSize;
	}
	public float getDailyChange() {
		return dailyChange;
	}
	public void setDailyChange(float dailyChange) {
		this.dailyChange = dailyChange;
	}
	public float getDailyChangeRelative() {
		return dailyChangeRelative;
	}
	public void setDailyChangeRelative(float dailyChangeRelative) {
		this.dailyChangeRelative = dailyChangeRelative;
	}
	public float getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	
	public Ticker() {
		super();
	}
	
	public Ticker(long id, float bid, float bidSize, float ask, float askSize, float dailyChange,
			float dailyChangeRelative, float lastPrice, float volume, float high, float low) {
		super();
		this.id = id;
		this.bid = bid;
		this.bidSize = bidSize;
		this.ask = ask;
		this.askSize = askSize;
		this.dailyChange = dailyChange;
		this.dailyChangeRelative = dailyChangeRelative;
		this.lastPrice = lastPrice;
		this.volume = volume;
		this.high = high;
		this.low = low;
	}


}
