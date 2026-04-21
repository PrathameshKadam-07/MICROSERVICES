package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.HotelClient;
import com.service.RatingClient;
import com.service.UserService;
import com.dto.HotelDto;
import com.dto.Ratingdto;
import com.entity.User;

@RestController
@RequestMapping("/api")
public class Usercontroller {

	@Autowired
	UserService us;
	
	@Autowired
	RatingClient rc;
	
	@Autowired
	HotelClient ht;
	 
	@PostMapping
	public ResponseEntity<User>  saveUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(us.saveUser(user));
	}
	
		@GetMapping("/getalluser")
		public ResponseEntity<List<User>> getAllUser() {
			List<User> userlist = us.getalluser();
			
			userlist.forEach(user -> {
				List<Ratingdto> rating = rc.getRatingByUserid(user.getUserid());
				
				rating.forEach(rat -> {
					HotelDto hotel = ht.getbyId(rat.getHotelId());
					rat.setHotel(hotel);
				});
				
				user.setRating(rating);
			});
			
			return ResponseEntity.ok(userlist);
		}
	
		
	@GetMapping("/getUserByid")
	public ResponseEntity<User> getUserById(@RequestParam("userId") String userId) {
		User user = us.getuserByUserId(userId);
		
		List<Ratingdto> rating = rc.getRatingByUserid(user.getUserid()) ;   

	    List<Ratingdto> updatedRatings = rating.stream().map(rat -> {
	        HotelDto hotel = ht.getbyId(rat.getHotelId()); // Feign call
	        rat.setHotel(hotel); // set hotel inside rating
	        return rat;
	    }).collect(Collectors.toList());

		user.setRating(updatedRatings);

		return  ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUserById(@RequestParam("userId") String userId) {
		return ResponseEntity.ok(us.deletUserById(userId));
	}
}
