package com.poseidon.repositorie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poseidon.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
