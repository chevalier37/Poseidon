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

import com.poseidon.domain.RuleName;
import com.poseidon.repositories.RuleNameRepository;
import com.poseidon.services.RuleNameService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameTestUNIT {
	
	@Autowired
	private RuleNameRepository ruleNameRepository;
	
	@Autowired
	private RuleNameService ruleNameService;
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void findAllBidListTest() {
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		RuleName ruleName1 = new RuleName("name1", "description1", "json1", "template1", "sqlStr1", "sqlPart1");
		ruleNameRepository.save(ruleName);
		ruleNameRepository.save(ruleName1);
		
		List<RuleName> ListRatingExpected = new ArrayList<>();
		ListRatingExpected.add(ruleName);
		ListRatingExpected.add(ruleName1);
		
		List<RuleName> ListRating = ruleNameService.findAllRuleName();
		
		assertEquals(ListRatingExpected.size(), ListRating.size());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void addBidTest() {
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		ruleNameService.addRuleName(ruleName);
		
		assertEquals("json", ruleNameRepository.findById(1).get().getJson());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void getBidListByIdTest() {
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		ruleNameRepository.save(ruleName);
		
		assertEquals("json", ruleNameService.getRuleNameById(1).get().getJson());
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void deleteBidListTest() {
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		ruleNameRepository.save(ruleName);
		ruleNameService.deleteRuleName(1);
		
		Optional<RuleName> ruleNameOpt = ruleNameRepository.findById(1);
		
		Assert.assertFalse(ruleNameOpt.isPresent());
	}

}
