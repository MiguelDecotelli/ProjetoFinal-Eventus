package com.eventus.eventus;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;

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

import com.eventus.eventus.controller.CityController;
import com.eventus.eventus.dto.CityDTO;
import com.eventus.eventus.model.CityModel;
import com.eventus.eventus.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(CityController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CityControllerTests {
	private MockMvc mockMvc;
	@MockBean
	private CityService service;
	@InjectMocks
	private CityController controller;
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

	@WithMockUser(roles="ADMIN")
	@Test
	public void testGetAllCities() throws Exception {
		CityModel cityModel = new CityModel(1, "Testelandia", "TS");
		when(service.readAllCities()).thenReturn(ResponseEntity.ok(List.of(cityModel)));
		mockMvc.perform(get("/api/cities"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCitiesById() throws Exception {
		CityModel cityModel = new CityModel(1, "Testelandia", "TS");
		when(service.readCityById(1)).thenReturn(ResponseEntity.ok(cityModel));
		mockMvc.perform(get("/api/cities/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateCities() throws Exception {
		CityDTO cityDto = new CityDTO("Testelandia", "TS");
		CityModel cityModel = new CityModel(1, "Testelandia", "TS");
		when(service.createCity(cityDto)).thenReturn(ResponseEntity.ok(cityModel));
		mockMvc
				.perform(post("/api/cities").content(objectMapper.writeValueAsString(cityDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateCitiesWithInvalidData() throws Exception {
		CityDTO cityDto = null;
		when(service.createCity(cityDto)).thenReturn(ResponseEntity.badRequest().build());
		mockMvc
				.perform(post("/api/cities"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testUpdateCities() throws Exception {
		CityDTO cityDto = new CityDTO("Testelandia", "TS");
		CityModel cityModel = new CityModel(1, "Testelandia", "TS");
		when(service.updateCity(1, cityDto)).thenReturn(ResponseEntity.ok(cityModel));
		mockMvc
				.perform(put("/api/cities/1").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cityDto)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Testelandia"));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteCities() throws Exception {
		when(service.deleteCity(1)).thenReturn(ResponseEntity.ok().build());
		mockMvc.perform(delete("/api/cities/1")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteCitiesWithoutExists() throws Exception {
		when(service.deleteCity(1)).thenReturn(ResponseEntity.notFound().build());
		mockMvc.perform(delete("/api/cities/1")).andExpect(status().isNotFound());
	}
}
