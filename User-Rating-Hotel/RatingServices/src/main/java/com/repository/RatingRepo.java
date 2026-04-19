package com.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entity.RatingEntity;

@Repository
public interface RatingRepo extends MongoRepository<RatingEntity,String> {

	List<RatingEntity> findByUserId(String userId);
	
	List<RatingEntity> findByHotelId(String hotelId);
	

	
}
