package com.eventus.eventus.dto;

import com.eventus.eventus.model.UserModel;

public record UserLogedDTO(String token, UserModel user) {
	public String getToken() {
		return token;
	}

	public UserModel getUser() {
		return user;
	}
}
