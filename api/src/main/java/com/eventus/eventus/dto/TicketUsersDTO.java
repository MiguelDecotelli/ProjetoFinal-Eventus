package com.eventus.eventus.dto;

import java.util.Set;

public record TicketUsersDTO(TicketDTO ticket, Set<UserDTO> users) {
	public Set<UserDTO> getUsers() {
		return users;
	}
	public TicketDTO getTicket() {
		return ticket;
	}
}
