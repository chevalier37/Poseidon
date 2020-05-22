package com.poseidon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.model.BidList;
import com.poseidon.repositorie.BidListRepository;

@Service("bidListService")
public class BidListService {

	@Autowired
	private BidListRepository bidListRepository;

	public List<BidList> findAllBidList() {
		return bidListRepository.findAll();
	}

	public BidList addBid(BidList bid) {
		return bidListRepository.save(bid);
	}

	public Optional<BidList> getBidListById(int id) {
		return bidListRepository.findById(id);
	}

	public void deleteBidList(int id) {
		bidListRepository.deleteById(id);
	}

}
