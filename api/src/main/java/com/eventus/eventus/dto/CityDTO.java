package com.eventus.eventus.dto;

public record CityDTO(String name, String state) {
	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}
}
