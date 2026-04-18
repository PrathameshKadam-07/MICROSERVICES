package com.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.Ratingdto;


@FeignClient(name = "RATINGSERVICES")
public interface RatingClient {

	@GetMapping("rating/getRatingByUserId")
	List<Ratingdto> getRatingByUserid(@RequestParam("userId") String userId);
	
}
