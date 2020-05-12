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

import com.poseidon.domain.Rating;
import com.poseidon.services.RatingService;

@RestController
public class RatingController {
	
	@Autowired
	private RatingService ratingService;

	@GetMapping("/rating/list")
    public List<Rating> home(){
        return ratingService.findAllRating();
    }

    @PostMapping("/rating/add")
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    @GetMapping("/rating/findId/{id}")
    public Optional<Rating> showUpdateForm(@PathVariable("id") Integer id) {
        return ratingService.getRatingById(id);
    }

    @PutMapping("/rating/update/{id}")
    public Rating updateRating(@PathVariable("id") Integer id, @RequestBody Rating rating) {
    	rating.setId(id);
        return ratingService.addRating(rating);
    }

    @DeleteMapping("/rating/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<Rating> rating = ratingService.getRatingById(id);
    	int idRating = rating.get().getId();
    	ratingService.deleteRating(idRating);
    }

}
