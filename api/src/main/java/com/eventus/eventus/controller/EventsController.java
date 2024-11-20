package com.eventus.eventus.controller;

import com.eventus.eventus.dto.EventsDTO;
import com.eventus.eventus.dto.NewEventsDTO;
import com.eventus.eventus.service.EventsService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventsController {

	@Autowired
	private EventsService eventsService;

	@Operation(summary = "Retorna uma lista contendo todos os eventos", description = "Busca no banco de dados todos os eventos cadastrados no sistema e retorna eles em uma lista")
	@GetMapping
	public ResponseEntity<List<EventsDTO>> getAllEvents() {
		return eventsService.getAllEvents();
	}

	@Operation(summary = "Retorna o evento solicitado", description = "Busca no banco de dados o evento solicitado via ID, e retorna ele caso existir")
	@GetMapping("/{id}")
	public ResponseEntity<EventsDTO> getEventsById(@PathVariable int id) {
		return eventsService.getEventsById(id);
	}

	@Operation(summary = "Executa a criação de um evento novo", description = "Cria um evento novo com os dados passados pelo body e retorna o novo evento junto com ID")
	@PostMapping
	public ResponseEntity<EventsDTO> createEvents(@RequestBody NewEventsDTO eventsDTO) {
		return eventsService.createEvents(eventsDTO);
	}

	@Operation(summary = "Atualiza informações de um evento", description = "Atualiza informações do evento requisitado via URI, com os dados passados pelo body")
	@PutMapping("/{id}")
	public ResponseEntity<EventsDTO> updateEvents(@PathVariable int id, @RequestBody NewEventsDTO eventsDTO) {
		return eventsService.updateEvents(id, eventsDTO);
	}

	@Operation(summary = "Deleta um evento do banco de dados", description = "Deleta o evento requisitado via URI")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEvents(@PathVariable int id) {
		return eventsService.deleteEvents(id);
	}
}
