package com.poseidon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.model.Rating;
import com.poseidon.repositorie.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	public List<Rating> findAllRating(){
		return ratingRepository.findAll();
	}
	
	public Rating addRating(Rating rating) {
		return ratingRepository.save(rating);
	}
	
	public Optional<Rating> getRatingById(int id) {
		return ratingRepository.findById(id);
	}
	
	public void deleteRating(int id) {
		ratingRepository.deleteById(id);
	}

}
