package com.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Hotel;
import com.exception.ResourceNotFoundException;
import com.repository.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepo hr;
	
	@Override
	public Hotel saveHotel(Hotel ht) {
		String id = UUID.randomUUID().toString();
		ht.setId(id);
		
		return hr.save(ht);
	}

	@Override
	public List<Hotel> getAllhotel() {
		
		
		return hr.findAll();
	}

	@Override
	public Hotel getHotelbyId(String id) {
		
		return hr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
	}

}
