package com.poseidon.repositorie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poseidon.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
