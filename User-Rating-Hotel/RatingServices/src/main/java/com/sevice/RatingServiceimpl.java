package com.sevice;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.RatingEntity;
import com.repository.RatingRepo;

@Service
public class RatingServiceimpl implements RatingService {

	@Autowired 
	RatingRepo rr;
	
	@Override
	public RatingEntity setRating(RatingEntity re) {
		String id = UUID.randomUUID().toString();
		re.setRatingId(id);
		
		return rr.save(re);
	}

	@Override
	public List<RatingEntity> getAllRating() {
		return rr.findAll();
	}

	@Override
	public List<RatingEntity> getByUserId(String userId) {
		return rr.findByUserId(userId);
	}

	@Override
	public List<RatingEntity> getByHoelId(String hotelId) {
		return rr.findByHotelId(hotelId);
	}

}
