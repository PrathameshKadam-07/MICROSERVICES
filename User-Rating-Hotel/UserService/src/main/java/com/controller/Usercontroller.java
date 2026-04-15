package com.controller;

import java.util.List;

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

import com.service.UserService;
import com.entity.User;

@RestController
@RequestMapping("/api")
public class Usercontroller {

	@Autowired
	UserService us;
	
	@PostMapping
	public ResponseEntity<User>  saveUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(us.saveUser(user));
	}
	
	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(us.getalluser());
	}
	
	@GetMapping("/getUserByid")
	public ResponseEntity<User> getUserById(@RequestParam("userId") String userId) {
		return  ResponseEntity.ok(us.getuserByUserId(userId));
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUserById(@RequestParam("userId") String userId) {
		return ResponseEntity.ok(us.deletUserById(userId));
	}
}
