package com.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.Ratingdto;


@FeignClient(name = "RATINGSERVICES")
public interface RatingClient {

	@GetMapping("rating/getRatingByUserId")
	List<Ratingdto> getRatingByUserid(@RequestParam("userId") String userId);
	
	@PostMapping("/rating")
	ResponseEntity<Ratingdto> saveRating(Ratingdto re);
	
//	IF YOU DONT HAVE USER DEFINE VALUES;
//	ResponseEntity<Ratingdto> saveRating(map<String,Objects> values);
//	ResponseEntity<Ratingdto> saveRating(@PathVariable("id") String id,RatingDto re);
	
}
