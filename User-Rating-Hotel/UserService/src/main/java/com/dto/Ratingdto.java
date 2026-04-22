package com.dto;

import jakarta.persistence.Transient;
import lombok.Builder;

@Builder
public class Ratingdto {

	private String ratingId;
	private String userId;
	private String hotelId;
	private String rating;
	
	@Transient
	private HotelDto hotel;
	
	public Ratingdto() {
		super();
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

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}
}
