package com.example.demo.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Ticker;


@Repository
public interface TickerRepository extends JpaRepository<Ticker, Long>{

	
	
}
