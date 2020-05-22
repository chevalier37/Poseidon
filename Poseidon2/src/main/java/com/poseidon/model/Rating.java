package com.poseidon.model;

import javax.persistence.*;


@Entity
@Table(name = "rating")
public class Rating {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="moodysRating")
	private String moodysRating;
	
	@Column(name="sandPRating")
	private String sandPRating;
	
	@Column(name="fitchRating")
	private String fitchRating;
	
	@Column(name="orderNumber")
	private Integer orderNumber;
	
	public Rating() {
	}

	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	public Integer getId() {
		return id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	
	
}
