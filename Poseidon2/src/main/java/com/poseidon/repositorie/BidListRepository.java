package com.poseidon.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poseidon.model.BidList;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
