package com.poseidon.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseidon.model.BidList;
import com.poseidon.service.BidListService;

@RestController
public class BidListController {

	@Autowired
	private BidListService bidListService;

	private static final Logger logger = LogManager.getRootLogger();

	@PostMapping("/bidList/add")
	public BidList addBidForm(@RequestBody BidList bid) {
		logger.info("Add bidList : {}", bid);
		return bidListService.addBid(bid);
	}

	@GetMapping("/bidList/findId/{id}")
	public Optional<BidList> showUpdateForm(@PathVariable("id") Integer id) {
		Optional<BidList> bidlist = bidListService.getBidListById(id);
		logger.info("Get bidList : {}", bidlist);
		return bidlist;
	}

	@PutMapping("/bidList/update/{id}")
	public BidList updateBid(@PathVariable("id") Integer id, @RequestBody BidList bidList) {
		bidList.setBidListId(id);
		logger.info("Update bidList : {}", bidList);
		return bidListService.addBid(bidList);
	}

	@DeleteMapping("/bidList/delete/{id}")
	public void deleteBid(@PathVariable("id") Integer id) {
		Optional<BidList> bid = bidListService.getBidListById(id);
		int idBid = bid.get().getBidListId();
		logger.info("Delete bidList : {}", bid);
		bidListService.deleteBidList(idBid);
	}
}
