package com.services;

import java.util.List;

import com.entity.Hotel;

public interface HotelService {

	 Hotel saveHotel(Hotel ht);
	
	 List<Hotel> getAllhotel();
	
	 Hotel getHotelbyId(String id);
}
