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

import com.poseidon.domain.Rating;
import com.poseidon.repositories.RatingRepository;
import com.poseidon.services.RatingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTestUNIT {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private RatingService ratingService;
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void findAllBidListTest() {
		Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
		Rating rating1 = new Rating("moodysRating1", "sandPRating1", "fitchRating1", 20);
		ratingRepository.save(rating);
		ratingRepository.save(rating1);
		
		List<Rating> ListRatingExpected = new ArrayList<>();
		ListRatingExpected.add(rating);
		ListRatingExpected.add(rating1);
		
		List<Rating> ListRating = ratingService.findAllRating();
		
		assertEquals(ListRatingExpected.size(), ListRating.size());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void addBidTest() {
		Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
		ratingService.addRating(rating);
		
		assertEquals(10, ratingRepository.findById(1).get().getOrderNumber());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void getBidListByIdTest() {
		Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
		ratingRepository.save(rating);
		
		assertEquals(10, ratingService.getRatingById(1).get().getOrderNumber());
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void deleteBidListTest() {
		Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 10);
		ratingRepository.save(rating);
		
		ratingService.deleteRating(1);
		Optional<Rating> ratingOpt = ratingRepository.findById(1);
		
		Assert.assertFalse(ratingOpt.isPresent());
	}

}
