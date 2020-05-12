package com.poseidon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseidon.domain.BidList;
import com.poseidon.services.BidListService;

import java.util.List;
import java.util.Optional;


@RestController
public class BidListController {

	@Autowired
	private BidListService bidListService;

	@GetMapping("/bidList/list")
    public List<BidList> home(){
        return bidListService.findAllBidList();
    }

    @PostMapping("/bidList/add")
    public BidList addBidForm(@RequestBody BidList bid) {
        return bidListService.addBid(bid);
    }

    @GetMapping("/bidList/findId/{id}")
    public Optional<BidList> showUpdateForm(@PathVariable("id") Integer id) {
        return bidListService.getBidListById(id);
    }

    @PutMapping("/bidList/update/{id}")
    public BidList updateBid(@PathVariable("id") Integer id, @RequestBody BidList bidList) {
    	bidList.setBidListId(id);
        return bidListService.addBid(bidList);
    }

    @DeleteMapping("/bidList/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<BidList> bid = bidListService.getBidListById(id);
    	int idBid = bid.get().getBidListId();
    	bidListService.deleteBidList(idBid);
    }
}
