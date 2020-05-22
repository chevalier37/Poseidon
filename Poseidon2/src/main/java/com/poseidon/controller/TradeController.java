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

import com.poseidon.model.Trade;
import com.poseidon.service.TradeService;

@RestController
public class TradeController {
	
	@Autowired
	private TradeService tradeService;
	
	private static final Logger logger = LogManager.getRootLogger();


    @PostMapping("/trade/add")
    public Trade addTrade(@RequestBody Trade trade) {
    	logger.info("Add trade : {}" , trade);
        return tradeService.addTrade(trade);
    }

    @GetMapping("/trade/findId/{id}")
    public Optional<Trade> showUpdateForm(@PathVariable("id") Integer id) {
    	Optional<Trade> trade = tradeService.getTradeById(id);
    	logger.info("Get trade : {}" , trade);
        return trade;
    }

    @PutMapping("/trade/update/{id}")
    public Trade updateBid(@PathVariable("id") Integer id, @RequestBody Trade trade) {
    	trade.setTradeId(id);
    	logger.info("Update trade : {}" , trade);
        return tradeService.addTrade(trade);
    }

    @DeleteMapping("/trade/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<Trade> trade = tradeService.getTradeById(id);
    	int tradeId = trade.get().getTradeId();
    	logger.info("Delete trade : {}" , trade);
    	tradeService.deletetrade(tradeId);
    }

}
