package com.eventus.eventus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventus.eventus.dto.TicketDTO;
import com.eventus.eventus.model.TicketModel;
import com.eventus.eventus.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	@Autowired
	private TicketService service;
	@GetMapping
	public ResponseEntity<List<TicketDTO>> getAllTickets(){
		return service.readAllTickets();
	}
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> getTicketById(@PathVariable int id){
		return service.readTicketById(id);
	}
	@PostMapping
	public ResponseEntity<TicketModel> postTicket(@RequestBody TicketDTO data){
		return service.createTicket(data);
	}
	@PutMapping("/{id}")
	public ResponseEntity<TicketDTO> putTicket(@PathVariable int id, @RequestBody TicketDTO data){
		return service.updateTicket(id, data);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity deleteTicket(@PathVariable int id){
		return service.deleteTicket(id);
	}
}
