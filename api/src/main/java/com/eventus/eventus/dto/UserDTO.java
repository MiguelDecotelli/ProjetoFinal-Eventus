package com.eventus.eventus.dto;

import com.eventus.eventus.model.CityModel;

import java.util.Date;

public record UserDTO ( int id, String username, String password, String email, String name, String lastname, Date birthday, String role, CityModel city){
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getLastname() {
		return lastname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getRole() {
		return role;
	}
	public CityModel getCity() {
		return city;
	}
}
