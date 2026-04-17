package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Hotel;
import com.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HomeController {

	@Autowired
	private HotelService hs;
	
	@PostMapping
public ResponseEntity<Hotel> saveHotel (@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.CREATED).body(hs.saveHotel(hotel));
	}
	
	@GetMapping
public ResponseEntity<List<Hotel>> getALlHotel (){
		return ResponseEntity.ok(hs.getAllhotel());
	}
	
	@GetMapping("/getBYid")
public ResponseEntity<Hotel> getbyId (@RequestParam("id") String id){
		return ResponseEntity.ok(hs.getHotelbyId(id));
	}
	
}
