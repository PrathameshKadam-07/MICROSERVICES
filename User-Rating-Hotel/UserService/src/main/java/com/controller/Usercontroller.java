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

import com.service.RatingClient;
import com.service.UserService;
import com.entity.User;

@RestController
@RequestMapping("/api")
public class Usercontroller {

	@Autowired
	UserService us;
	
	@Autowired
	RatingClient rc;
	
	@PostMapping
	public ResponseEntity<User>  saveUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(us.saveUser(user));
	}
	
	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userlist = us.getalluser();

		List<User> userlistup =  userlist.stream().map(user -> {user.setRating(rc.getRatingByUserid(user.getUserid()));
				return user;}).collect(Collectors.toList());
		
		return ResponseEntity.ok(userlistup);
	}
	
	@GetMapping("/getUserByid")
	public ResponseEntity<User> getUserById(@RequestParam("userId") String userId) {
		User user = us.getuserByUserId(userId);
		user.setRating(rc.getRatingByUserid(user.getUserid()));

		return  ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUserById(@RequestParam("userId") String userId) {
		return ResponseEntity.ok(us.deletUserById(userId));
	}
}
