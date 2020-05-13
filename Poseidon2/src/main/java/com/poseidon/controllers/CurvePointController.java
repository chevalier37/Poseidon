package com.poseidon.controllers;

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

import com.poseidon.domain.CurvePoint;
import com.poseidon.services.CurvePointService;

@RestController
public class CurvePointController {
	
	@Autowired
	private CurvePointService curvePointservice;
	
	private static final Logger logger = LogManager.getRootLogger();


    @PostMapping("/curvePoint/add")
    public CurvePoint addBidForm(@RequestBody CurvePoint curvePoint) {
    	logger.info("Add curvePoint : {}" , curvePoint);
        return curvePointservice.addcurvePoint(curvePoint);
    }

    @GetMapping("/curvePoint/findId/{id}")
    public Optional<CurvePoint> showUpdateForm(@PathVariable("id") Integer id) {
    	Optional<CurvePoint> curvePoint = curvePointservice.getCurvePointById(id);
    	logger.info("Get curvePoint : {}" , curvePoint);
        return curvePoint;
    }

    @PutMapping("/curvePoint/update/{id}")
    public CurvePoint updateBid(@PathVariable("id") Integer id, @RequestBody CurvePoint curvePoint) {
    	curvePoint.setId(id);
    	logger.info("Update curvePoint : {}" , curvePoint);
        return curvePointservice.addcurvePoint(curvePoint);
    }

    @DeleteMapping("/curvePoint/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<CurvePoint> curvePoint = curvePointservice.getCurvePointById(id);
    	int idCurvePoint = curvePoint.get().getCurveId();
    	logger.info("Delete curvePoint : {}" , curvePoint);
    	curvePointservice.deletecurvePoint(idCurvePoint);
    }

}
