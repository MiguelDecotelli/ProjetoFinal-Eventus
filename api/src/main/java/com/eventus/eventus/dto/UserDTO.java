package com.eventus.eventus.dto;

import java.util.Date;

public record UserDTO (int id, String username, String password, String email, String name, String lastname, Date birthday, String role, CityDTO city){
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
	public CityDTO getCity() {
		return city;
	}
}
