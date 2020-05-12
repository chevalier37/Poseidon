package com.poseidon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.poseidon.domain.Trade;
import com.poseidon.repositories.TradeRepository;

@Service
public class TradeService {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	public List<Trade> findAllTrade(){
		return tradeRepository.findAll();
	}
	
	public Trade addTrade(Trade trade) {
		return tradeRepository.save(trade);
	}
	
	public Optional<Trade> getTradeById(int id) {
		return tradeRepository.findById(id);
	}
	
	public void deletetrade(int id) {
		tradeRepository.deleteById(id);
	}

}
