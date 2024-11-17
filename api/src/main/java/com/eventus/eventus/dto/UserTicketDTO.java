package com.eventus.eventus.dto;

public record UserTicketDTO(int id, UserDTO user, TicketDTO ticket) {
	public int id() {
		return id;
	}
	public UserDTO user() {
		return user;
	}
	public TicketDTO ticket() {
		return ticket;
	}
}
