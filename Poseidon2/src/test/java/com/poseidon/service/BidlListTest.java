package com.poseidon.service;

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

import com.poseidon.domain.BidList;
import com.poseidon.repositories.BidListRepository;
import com.poseidon.services.BidListService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidlListTest {
	
	@Autowired
	private BidListRepository bidListRepository;
	
	@Autowired
	private BidListService bidListService;
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void findAllBidListTest() {
		BidList bid1 = new BidList("Account Test", "Type Test", 10d);
		BidList bid2 = new BidList("Account Test1", "Type Test1", 20d);
		bidListRepository.save(bid1);
		bidListRepository.save(bid2);
		
		List<BidList> ListBidListExpected = new ArrayList<>();
		ListBidListExpected.add(bid1);
		ListBidListExpected.add(bid2);
		
		List<BidList> ListBidList = bidListService.findAllBidList();
		
		assertEquals(ListBidListExpected.size(), ListBidList.size());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void addBidTest() {
		BidList bid1 = new BidList("Account Test", "Type Test", 10d);
		bidListService.addBid(bid1);
		
		assertEquals("Account Test", bidListRepository.findById(1).get().getAccount());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void getBidListByIdTest() {
		BidList bid1 = new BidList("Account Test", "Type Test", 10d);
		bidListRepository.save(bid1);
		
		assertEquals("Account Test", bidListService.getBidListById(1).get().getAccount());
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void deleteBidListTest() {
		BidList bid1 = new BidList("Account Test", "Type Test", 10d);
		bidListRepository.save(bid1);
		
		bidListService.deleteBidList(1);
		Optional<BidList> bidList = bidListRepository.findById(1);
		
		Assert.assertFalse(bidList.isPresent());
	}
	
	

}
