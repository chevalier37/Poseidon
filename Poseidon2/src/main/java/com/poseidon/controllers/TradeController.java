package com.poseidon.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseidon.domain.Trade;
import com.poseidon.services.TradeService;

@RestController
public class TradeController {
	
	@Autowired
	private TradeService tradeService;

	@GetMapping("/trade/list")
    public List<Trade> home(){
        return tradeService.findAllTrade();
    }

    @PostMapping("/trade/add")
    public Trade addTrade(@RequestBody Trade trade) {
        return tradeService.addTrade(trade);
    }

    @GetMapping("/trade/findId/{id}")
    public Optional<Trade> showUpdateForm(@PathVariable("id") Integer id) {
        return tradeService.getTradeById(id);
    }

    @PutMapping("/trade/update/{id}")
    public Trade updateBid(@PathVariable("id") Integer id, @RequestBody Trade trade) {
    	trade.setTradeId(id);
        return tradeService.addTrade(trade);
    }

    @DeleteMapping("/trade/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<Trade> trade = tradeService.getTradeById(id);
    	int tradeId = trade.get().getTradeId();
    	tradeService.deletetrade(tradeId);
    }

}
