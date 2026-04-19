package com.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("user_rating")
public class RatingEntity {

	@Id
	private String ratingId;
	private String userId;  
	private String hotelId;
	private String rating;

	
	public RatingEntity() {
		super();
	}
	public RatingEntity(String ratingId, String userId, String hotelId, String rating) {
		super();
		this.ratingId = ratingId;
		this.userId = userId;
		this.hotelId = hotelId;
		this.rating = rating;
	}
	public String getRatingId() {
		return ratingId;
	}
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
}

