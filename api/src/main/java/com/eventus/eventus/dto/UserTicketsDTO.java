package com.eventus.eventus.dto;

import java.util.Set;

public record UserTicketsDTO(UserDTO user, Set<TicketDTO> tickets) {
	public UserDTO getUser() {
		return user;
	}
	public Set<TicketDTO> getTickets() {
		return tickets;
	}
}
