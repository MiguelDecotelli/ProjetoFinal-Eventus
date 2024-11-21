package com.eventus.eventus;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eventus.eventus.controller.EventsController;
import com.eventus.eventus.dto.EventsDTO;
import com.eventus.eventus.dto.NewEventsDTO;
import com.eventus.eventus.model.EventsStatus;
import com.eventus.eventus.service.EventsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(EventsController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventsControllerTests {
	private MockMvc mockMvc;
	@MockBean
	private EventsService service;
	@InjectMocks
	private EventsController controller;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
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
	public void testGetAllEvents() throws Exception {
		EventsDTO dto = new EventsDTO(
				1, "Show de Testes", format.parse("2024-02-29"), format.parse("2024-02-30"),
				"Testes de eventos", "", EventsStatus.ACTIVE, null);
		when(service.getAllEvents()).thenReturn(ResponseEntity.ok(List.of(dto)));
		mockMvc.perform(get("/api/events"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testEventsById() throws Exception {
		EventsDTO dto = new EventsDTO(
				1, "Show de Testes", format.parse("2024-02-29"), format.parse("2024-02-30"),
				"Testes de eventos", "", EventsStatus.ACTIVE, null);
		when(service.getEventsById(1)).thenReturn(ResponseEntity.ok(dto));
		mockMvc.perform(get("/api/events/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateEvents() throws Exception {
		EventsDTO dto = new EventsDTO(
				1, "Show de Testes", format.parse("2024-02-29"), format.parse("2024-02-30"),
				"Testes de eventos", "", EventsStatus.ACTIVE, null);
		NewEventsDTO newData = new NewEventsDTO(
				1, "Show de Testes", format.parse("2024-02-29"), format.parse("2024-02-30"),
				"Testes de eventos", "", EventsStatus.ACTIVE, 0);
		when(service.createEvents(newData)).thenReturn(ResponseEntity.ok(dto));
		mockMvc.perform(post("/api/events")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(newData))
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateEventsWithInvalidData() throws Exception {
		when(service.createEvents(null)).thenReturn(ResponseEntity.badRequest().build());
		mockMvc.perform(post("/api/events"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testUpdateEvents() throws Exception {
		EventsDTO dto = new EventsDTO(
				1, "Show de Testes", format.parse("2024-02-29"), format.parse("2024-02-30"),
				"Testes de eventos", "", EventsStatus.ACTIVE, null);
		NewEventsDTO newData = new NewEventsDTO(
				1, "Show de Testes", format.parse("2024-02-29"), format.parse("2024-02-30"),
				"Testes de eventos", "", EventsStatus.ACTIVE, 0);
		when(service.updateEvents(1, newData)).thenReturn(ResponseEntity.ok(dto));
		mockMvc
				.perform(put("/api/events/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(newData))
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteEvents() throws Exception {
		when(service.deleteEvents(1)).thenReturn(ResponseEntity.ok().build());
		mockMvc.perform(delete("/api/events/1"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteEventsWithoutExists() throws Exception {
		when(service.deleteEvents(1)).thenReturn(ResponseEntity.notFound().build());
		mockMvc.perform(delete("/api/events/1"))
				.andExpect(status().isNotFound());
	}
}
