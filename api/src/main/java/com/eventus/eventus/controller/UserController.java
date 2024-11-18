package com.eventus.eventus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.dto.UserTicketDTO;
import com.eventus.eventus.dto.UserTicketsDTO;
import com.eventus.eventus.model.UsersTicketsModel;
import com.eventus.eventus.service.UserService;
import com.eventus.eventus.service.UsersTicketsService;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private UsersTicketsService userTicketsService;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		return service.createUser(userDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
		return service.updateUser(id, userDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}

	@GetMapping("/{id}/tickets")
	public ResponseEntity<UserTicketsDTO> getAllUserTickets(@PathVariable int id) {
		return userTicketsService.getAllTicketsByUser(id);
	}

	@GetMapping("/{id}/tickets/{ticketId}")
	public ResponseEntity<UserTicketDTO> getUserTicket(@PathVariable int id, @PathVariable int ticketId) {
		return userTicketsService.getUserTicket(id, ticketId);
	}

	@PostMapping("/{id}/tickets/{ticketId}")
	public ResponseEntity<UsersTicketsModel> generateNewTicketForUser(@PathVariable int id, @PathVariable int ticketId) {
		return userTicketsService.createNewUserTicket(id, ticketId);
	}

	@DeleteMapping("/{id}/tickets/{ticketId}")
	public ResponseEntity<Object> cancelTicketForUser(@PathVariable int id, @PathVariable int ticketId) {
		return userTicketsService.cancelTicket(id, ticketId);
	}
}
