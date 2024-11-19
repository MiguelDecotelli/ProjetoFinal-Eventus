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
import com.eventus.eventus.dto.TicketUsersDTO;
import com.eventus.eventus.model.TicketModel;
import com.eventus.eventus.service.TicketService;
import com.eventus.eventus.service.UsersTicketsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	@Autowired
	private TicketService service;
	@Autowired
	private UsersTicketsService usersTicketsService;

	@Operation(summary = "Retorna uma lista com todos os tickets", description = "Busca no banco de dados todos os tickets criados no sistema e retorna eles em uma lista")
	@GetMapping
	public ResponseEntity<List<TicketDTO>> getAllTickets() {
		return service.readAllTickets();
	}

	@Operation(summary = "Retorna os dados do ticket solicitado", description = "Busca no banco de dados as informações do ticket informado via URI e retorna para o usuario caso existente")
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> getTicketById(@PathVariable int id) {
		return service.readTicketById(id);
	}

	@Operation(summary = "Cria um novo ticket", description = "Cria um novo ticket no banco de dados com os dados informados no body, retornando as informações e o ID dele")
	@PostMapping
	public ResponseEntity<TicketModel> postTicket(@RequestBody TicketDTO data) {
		return service.createTicket(data);
	}

	@Operation(summary = "Atualiza os dados de um ticket", description = "Atualiza os dados do ticket informado via ID passado pelo URI, com as informações enviadas viabody")
	@PutMapping("/{id}")
	public ResponseEntity<TicketDTO> putTicket(@PathVariable int id, @RequestBody TicketDTO data) {
		return service.updateTicket(id, data);
	}

	@Operation(summary = "Deleta um ticket", description = "Deleta o ticket informado via ID passado pelo URI")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTicket(@PathVariable int id) {
		return service.deleteTicket(id);
	}

	@Operation(summary = "Retorna todos os usuarios do qual iniciaram transação com o ticket", description = "Busca no banco de dados todos os usuario do qual iniciaram transação com o ticket, independente se a transação foi concluida ou não")
	@GetMapping("/{id}/users")
	public ResponseEntity<TicketUsersDTO> getAllUsers(@PathVariable int id) {
		return usersTicketsService.getAllUsersByTicket(id);
	}
}
