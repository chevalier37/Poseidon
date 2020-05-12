package com.poseidon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.poseidon.domain.RuleName;
import com.poseidon.repositories.RuleNameRepository;

@Service
public class RuleNameService {
	
	@Autowired
	private RuleNameRepository ruleNameRepository;
	
	public List<RuleName> findAllRuleName(){
		return ruleNameRepository.findAll();
	}
	
	public RuleName addRuleName(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}
	
	public Optional<RuleName> getRuleNameById(int id) {
		return ruleNameRepository.findById(id);
	}
	
	public void deleteRuleName(int id) {
		ruleNameRepository.deleteById(id);
	}

}
