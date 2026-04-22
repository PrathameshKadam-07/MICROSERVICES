package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RatingEntity;
import com.sevice.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	RatingService rs;
	
	@PostMapping
     ResponseEntity<RatingEntity> saveRating(@RequestBody RatingEntity re) {
		return ResponseEntity.status(HttpStatus.CREATED).body(rs.setRating(re)); 
	}
	
	@GetMapping
	ResponseEntity<List<RatingEntity>> getAllRating() {
		return ResponseEntity.ok(rs.getAllRating()); 
	}
	
	@GetMapping("/getRatingByUserId")
	ResponseEntity<List<RatingEntity>> getRatingByUserid(@RequestParam("userId") String userId) {
		return ResponseEntity.ok(rs.getByUserId(userId));
	}
	
	@GetMapping("/getRatingByHotelId")
	ResponseEntity<List<RatingEntity>> getRatingByhotelid(@RequestParam("hotelId") String hotelId) {
		return ResponseEntity.ok(rs.getByUserId(hotelId));
	}
}
