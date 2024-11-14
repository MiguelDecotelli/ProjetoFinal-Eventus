package com.eventus.eventus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventus.eventus.dto.TicketDTO;
import com.eventus.eventus.model.TicketModel;
import com.eventus.eventus.repository.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository repository;
	public ResponseEntity<TicketModel> createTicket(TicketDTO data) {
		TicketModel model = new TicketModel(data.getName(), data.getDescription(), data.getAmount());
		try {
			TicketModel ticket = repository.save(model);
			return ResponseEntity.ok(ticket);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<List<TicketDTO>> readAllTickets(){
		try {
			List<TicketModel> allModels = repository.findAll();
			List<TicketDTO> allDtos = allModels.stream().map(this::convertToDTO).toList();
			return ResponseEntity.ok(allDtos);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<TicketDTO> readTicketById(int id) {
		try {
			Optional<TicketModel> ticket = repository.findById(id);
			if(ticket.isEmpty()) return ResponseEntity.notFound().build();
			return ResponseEntity.ok(convertToDTO(ticket.get()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<TicketDTO> updateTicket(int id, TicketDTO data) {
		Optional<TicketModel> ticket = repository.findById(id);
		if(ticket.isEmpty()) return ResponseEntity.notFound().build();
		ticket.get().setName(data.getName());
		ticket.get().setDescription(data.getDescription());
		ticket.get().setAmount(data.getAmount());
		try {
			TicketModel ticketUpdate = repository.save(ticket.get());
			return ResponseEntity.ok(convertToDTO(ticketUpdate));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity deleteTicket(int id) {
		Optional<TicketModel> ticket = repository.findById(id);
		if(ticket.isEmpty()) return ResponseEntity.notFound().build();
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	private TicketDTO convertToDTO(TicketModel model){
		TicketDTO dto = new TicketDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setDescription(model.getDescription());
		dto.setAmount(model.getAmount());
		return dto;
	}
}
