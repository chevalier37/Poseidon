package com.poseidon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.model.CurvePoint;
import com.poseidon.repositorie.CurvePointRepository;

@Service
public class CurvePointService {
	
	@Autowired
	private CurvePointRepository curvePointRepository;
	
	public List<CurvePoint> findAllcurvePoint(){
		return curvePointRepository.findAll();
	}
	
	public CurvePoint addcurvePoint(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}
	
	public Optional<CurvePoint> getCurvePointById(int id) {
		return curvePointRepository.findById(id);
	}
	
	public void deletecurvePoint(int id) {
		curvePointRepository.deleteById(id);
	}

}
