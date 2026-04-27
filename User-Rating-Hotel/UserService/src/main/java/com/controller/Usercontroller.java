package com.controller;

import java.util.ArrayList;
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

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;

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
//	@CircuitBreaker(name = "getAllUserBreaker",fallbackMethod = "fallbackgetAllUser")
//	@Retry(name = "getAllUserRetry",fallbackMethod = "fallbackgetAllUser")
	@RateLimiter(name = "getAllUserRateLimiter",fallbackMethod = "fallbackgetAllUser")
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

// Circuit break for getAllUserBreak is Handled here.
	public ResponseEntity<List<User>> fallbackgetAllUser(Exception ex){
		User user = new User();
		user.setUserid("dummy");
		user.setName("dummy");
		user.setEmail("dummy@gamil.com");
		user.setAbout("This user is created for GetALlUser due to Rating Service is Off : "+ex.getMessage());
		
		ArrayList<User> list = new ArrayList<>();
		list.add(user);
		
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(list);
	}
			
	@GetMapping("/getUserByid")
//	@CircuitBreaker(name = "getUserBYIdBreaker",fallbackMethod = "fallbackgetByUserId")
//	@Retry(name = "getUserBYIdRetry",fallbackMethod = "fallbackgetByUserId")
	@RateLimiter(name = "getUserBYIdRateLimiter",fallbackMethod = "fallbackgetByUserId")
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
	
// Circuit break for getUserById is Handled here.
		public ResponseEntity<User> fallbackgetByUserId(String uuid,Exception ex){
			User user = new User();
			user.setUserid(uuid);
			user.setName("dummy");
			user.setEmail("dummy@gamil.com");
			user.setAbout("This user for GETBYUSERID is created due to Rating Service is Off : "+ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(user);
		}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUserById(@RequestParam("userId") String userId) {
		return ResponseEntity.ok(us.deletUserById(userId));
	}	
	
//	calling post method of other Services.
	@PostMapping("/createRating")
	public ResponseEntity<Ratingdto> saveRating(){
		Ratingdto rating = new Ratingdto();
		rating.setRatingId("1");
		rating.setUserId("1");
		rating.setHotelId("1");
		rating.setRating("*****");
		
		return rc.saveRating(rating);
	}
}
