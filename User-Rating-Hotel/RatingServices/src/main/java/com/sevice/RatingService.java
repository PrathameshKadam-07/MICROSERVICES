package com.sevice;

import java.util.List;

import com.entity.RatingEntity;

public interface RatingService {

	RatingEntity setRating(RatingEntity re);
	
	List<RatingEntity> getAllRating();
	
	List<RatingEntity> getByUserId(String userId);
	
	List<RatingEntity> getByHoelId(String hotelId);
	
	
}
