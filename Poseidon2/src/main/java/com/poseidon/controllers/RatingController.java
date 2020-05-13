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

import com.poseidon.domain.Rating;
import com.poseidon.services.RatingService;

@RestController
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	private static final Logger logger = LogManager.getRootLogger();


    @PostMapping("/rating/add")
    public Rating addRating(@RequestBody Rating rating) {
    	logger.info("Add rating : {}" , rating);
        return ratingService.addRating(rating);
    }

    @GetMapping("/rating/findId/{id}")
    public Optional<Rating> showUpdateForm(@PathVariable("id") Integer id) {
    	Optional<Rating> rating = ratingService.getRatingById(id);
    	logger.info("Get rating : {}" , rating);
        return rating;
    }

    @PutMapping("/rating/update/{id}")
    public Rating updateRating(@PathVariable("id") Integer id, @RequestBody Rating rating) {
    	rating.setId(id);
    	logger.info("Update rating : {}" , rating);
        return ratingService.addRating(rating);
    }

    @DeleteMapping("/rating/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<Rating> rating = ratingService.getRatingById(id);
    	int idRating = rating.get().getId();
    	logger.info("Delete rating : {}" , rating);
    	ratingService.deleteRating(idRating);
    }

}
