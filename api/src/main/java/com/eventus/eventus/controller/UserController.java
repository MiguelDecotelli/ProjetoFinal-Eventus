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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private UsersTicketsService userTicketsService;

	@Operation(summary = "Retorna uma lista contendo todos os usuarios cadastrados do sistema", description = "Busca no banco de dados todos os usuarios do sistema e retorna eles numa lista. (Sem Dados sensiveis)")
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return service.getAllUsers();
	}

	@Operation(summary = "Retorna um usuario cadastrados via ID", description = "Busca no banco de dados o usuarios dono do ID e retorna ele no body.")
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}

	@Operation(summary = "Cria um usuario com nenhuma restrição de Role. (Rota Exclusiva de ADMIN)", description = "Executa a criação de um usuario sem nenhuma restrição na hora de escolher cargos, precisa estar authenticado como um administrador para usar essa rota")
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		return service.createUser(userDTO);
	}

	@Operation(summary = "Atualiza um usuario", description = "Atualiza as informações de um usuario com base no ID informado no URI, e com os dados informados pelo body")
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
		return service.updateUser(id, userDTO);
	}

	@Operation(summary = "Deleta um usuario cadastrado", description = "Deleta um usuario do banco de dados via ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}

	@Operation(summary = "Retorna uma lista com todas as relações de transação de tickets do usuario", description = "Busca no banco de dados todas as relações de transação de tickets do usuario pelo ID informado na URI")
	@GetMapping("/{id}/tickets")
	public ResponseEntity<UserTicketsDTO> getAllUserTickets(@PathVariable int id) {
		return userTicketsService.getAllTicketsByUser(id);
	}

	@Operation(summary = "Retorna as informações da relação do ticket e usuario caso ela exista", description = "Busca no banco de dados se existe a relação usuario ticket pelos IDs informados via URI")
	@GetMapping("/{id}/tickets/{ticketId}")
	public ResponseEntity<UserTicketDTO> getUserTicket(@PathVariable int id, @PathVariable int ticketId) {
		return userTicketsService.getUserTicket(id, ticketId);
	}

	@Operation(summary = "Gera uma nova solicitação de compra", description = "Gera uma nova relação de compra no banco de dados pelos IDs informados via URI")
	@PostMapping("/{id}/tickets/{ticketId}")
	public ResponseEntity<UsersTicketsModel> generateNewTicketForUser(@PathVariable int id, @PathVariable int ticketId) {
		return userTicketsService.createNewUserTicket(id, ticketId);
	}

	@Operation(summary = "Cancela a solicitação de compra de um usuario", description = "Executa o cancelamento da solicitação de compra de ticket do usuario, transformando a relação em CANCELED")
	@DeleteMapping("/{id}/tickets/{ticketId}")
	public ResponseEntity<Object> cancelTicketForUser(@PathVariable int id, @PathVariable int ticketId) {
		return userTicketsService.cancelTicket(id, ticketId);
	}
}
