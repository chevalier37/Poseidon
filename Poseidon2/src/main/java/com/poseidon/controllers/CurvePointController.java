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

import com.poseidon.domain.CurvePoint;
import com.poseidon.services.CurvePointService;

@RestController
public class CurvePointController {
	
	@Autowired
	private CurvePointService curvePointservice;

	@GetMapping("/curvePoint/list")
    public List<CurvePoint> home(){
        return curvePointservice.findAllcurvePoint();
    }

    @PostMapping("/curvePoint/add")
    public CurvePoint addBidForm(@RequestBody CurvePoint curvePoint) {
        return curvePointservice.addcurvePoint(curvePoint);
    }

    @GetMapping("/curvePoint/findId/{id}")
    public Optional<CurvePoint> showUpdateForm(@PathVariable("id") Integer id) {
        return curvePointservice.getCurvePointById(id);
    }

    @PutMapping("/curvePoint/update/{id}")
    public CurvePoint updateBid(@PathVariable("id") Integer id, @RequestBody CurvePoint curvePoint) {
    	curvePoint.setId(id);
        return curvePointservice.addcurvePoint(curvePoint);
    }

    @DeleteMapping("/curvePoint/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<CurvePoint> curvePoint = curvePointservice.getCurvePointById(id);
    	int idCurvePoint = curvePoint.get().getCurveId();
    	curvePointservice.deletecurvePoint(idCurvePoint);
    }

}
