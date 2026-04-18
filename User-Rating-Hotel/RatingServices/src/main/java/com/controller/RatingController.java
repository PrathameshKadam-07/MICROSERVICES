package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RatingEntity;
import com.sevice.RatingService;

@RestController
@RequestMapping("rating")
public class RatingController {

	@Autowired
	RatingService rs;
	
	@PostMapping
	RatingEntity saveRating(@RequestBody RatingEntity re) {
		return rs.setRating(re); 
	}
	
	@GetMapping
	List<RatingEntity> getAllRating() {
		return rs.getAllRating(); 
	}
	
	@GetMapping("getRatingByUserId")
	List<RatingEntity> getRatingByUserid(@RequestParam("userId") String userId) {
		return rs.getByUserId(userId);
	}
	
	@GetMapping("getRatingByHotelId")
	List<RatingEntity> getRatingByhotelid(@RequestParam("hotelId") String hotelId) {
		return rs.getByUserId(hotelId);
	}
}
