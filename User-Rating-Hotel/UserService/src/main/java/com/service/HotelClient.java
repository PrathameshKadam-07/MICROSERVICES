package com.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.HotelDto;



@FeignClient(name = "HOTELSERVICE")
public interface HotelClient {

@GetMapping("/hotel/getBYid")
HotelDto getbyId (@RequestParam("id") String id);
}
