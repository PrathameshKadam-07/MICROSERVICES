package com.dto;

public class HotelDto {
	
	private String id;
	private String name;
	private String locaion;
	private String about;
	
	public HotelDto() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocaion() {
		return locaion;
	}
	public void setLocaion(String locaion) {
		this.locaion = locaion;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
}
