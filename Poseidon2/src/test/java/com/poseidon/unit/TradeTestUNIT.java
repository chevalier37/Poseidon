package com.poseidon.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.poseidon.domain.Trade;
import com.poseidon.repositories.TradeRepository;
import com.poseidon.services.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTestUNIT {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private TradeService tradeService;
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void findAllBidListTest() {
		Trade trade = new Trade("account", "type");
		Trade trade1 = new Trade("account1", "type1");
		tradeRepository.save(trade);
		tradeRepository.save(trade1);
		
		List<Trade> ListTradeExpected = new ArrayList<>();
		ListTradeExpected.add(trade);
		ListTradeExpected.add(trade1);
		
		List<Trade> ListTrade = tradeService.findAllTrade();
		
		assertEquals(ListTradeExpected.size(), ListTrade.size());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void addBidTest() {
		Trade trade = new Trade("account", "type");
		tradeService.addTrade(trade);
		
		assertEquals("account", tradeRepository.findById(1).get().getAccount());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void getBidListByIdTest() {
		Trade trade = new Trade("account", "type");
		tradeRepository.save(trade);
		
		assertEquals("account", tradeService.getTradeById(1).get().getAccount());
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void deleteBidListTest() {
		Trade trade = new Trade("account", "type");
		tradeRepository.save(trade);
		
		tradeService.deletetrade(1);
		Optional<Trade> tradeOpt = tradeRepository.findById(1);
		
		Assert.assertFalse(tradeOpt.isPresent());
	}

}
