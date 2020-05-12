package com.poseidon.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseidon.domain.RuleName;
import com.poseidon.services.RuleNameService;

@RestController
public class RuleNameController {
	
	@Autowired
	private RuleNameService ruleNameService;


    @PostMapping("/ruleName/add")
    public RuleName addRuleName(@RequestBody RuleName ruleName) {
        return ruleNameService.addRuleName(ruleName);
    }

    @GetMapping("/ruleName/findId/{id}")
    public Optional<RuleName> showUpdateForm(@PathVariable("id") Integer id) {
        return ruleNameService.getRuleNameById(id);
    }

    @PutMapping("/ruleName/update/{id}")
    public RuleName updateBid(@PathVariable("id") Integer id, @RequestBody RuleName ruleName) {
    	ruleName.setId(id);
        return ruleNameService.addRuleName(ruleName);
    }

    @DeleteMapping("/ruleName/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<RuleName> ruleName = ruleNameService.getRuleNameById(id);
    	int ruleNameId = ruleName.get().getId();
    	ruleNameService.deleteRuleName(ruleNameId);
    }

}
