package com.eventus.eventus.dto;

import java.util.Date;

public record RegistrationDTO (String username, String password, String email, String name, String lastname, Date birthday){
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
}
