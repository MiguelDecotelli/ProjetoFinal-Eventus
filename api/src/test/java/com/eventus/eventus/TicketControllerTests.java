package com.eventus.eventus;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eventus.eventus.controller.TicketController;
import com.eventus.eventus.dto.TicketDTO;
import com.eventus.eventus.model.EventsModel;
import com.eventus.eventus.model.TicketModel;
import com.eventus.eventus.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebMvcTest(TicketController.class)
public class TicketControllerTests {
	private MockMvc mockMvc;
	@MockBean
	private TicketService service;
	@InjectMocks
	private TicketController controller;
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testGetAllTicket() throws Exception {
		TicketDTO dto = new TicketDTO(1, "Testes VIP", "Melhor ingresso dos testes", 10, 30f, 0);
		when(service.readAllTickets()).thenReturn(ResponseEntity.ok(List.of(dto)));
		mockMvc.perform(get("/api/tickets"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testTicketById() throws Exception {
		TicketDTO dto = new TicketDTO(1, "Testes VIP", "Melhor ingresso dos testes", 10, 30f, 0);
		when(service.readTicketById(1)).thenReturn(ResponseEntity.ok(dto));
		mockMvc.perform(get("/api/tickets/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateTicket() throws Exception {
		TicketDTO dto = new TicketDTO(1, "Testes VIP", "Melhor ingresso dos testes", 10, 30f,0);
		TicketModel model = new TicketModel(1, "Testes VIP", "Melhor ingresso dos testes", 10,null,30f);
		when(service.createTicket(dto)).thenReturn(ResponseEntity.ok(model));
		mockMvc
				.perform(
						post("/api/tickets")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateTicketWithInvalidData() throws Exception {
		when(service.createTicket(null)).thenReturn(ResponseEntity.badRequest().build());
		mockMvc.perform(post("/api/tickets"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testUpdateTicket() throws Exception {
		TicketDTO dto = new TicketDTO(1, "Testes VIP", "Melhor ingresso dos testes", 10, 30f, 0);
		when(service.updateTicket(1, dto)).thenReturn(ResponseEntity.ok(dto));
		mockMvc.perform(
				put("/api/tickets/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteTicket() throws Exception {
		when(service.deleteTicket(1)).thenReturn(ResponseEntity.ok().build());
		mockMvc.perform(delete("/api/tickets/1"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteTicketWithoutExists() throws Exception {
		when(service.deleteTicket(1)).thenReturn(ResponseEntity.notFound().build());
		mockMvc.perform(delete("/api/tickets/1"))
				.andExpect(status().isNotFound());
	}
}
