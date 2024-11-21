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

import com.eventus.eventus.controller.UserController;
import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.model.UserRole;
import com.eventus.eventus.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebMvcTest(UserController.class)
public class UserControllerTests {
	private MockMvc mockMvc;
	@MockBean
	private UserService service;
	@InjectMocks
	private UserController controller;
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
	public void testGetAllUsers() throws Exception {
		UserDTO userDTO = new UserDTO(
				1, "teste", "1234", "teste@teste.com", "teste", "teste", format.parse("2000-01-01"), "BASIC", null
				);

		when(service.getAllUsers()).thenReturn(ResponseEntity.ok(List.of(userDTO)));
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testUsersById() throws Exception {
		UserDTO userDTO = new UserDTO(
				1, "teste", "1234", "teste@teste.com", "teste", "teste", format.parse("2000-01-01"), "BASIC", null
				);
		when(service.getUserById(1)).thenReturn(ResponseEntity.ok(userDTO));
		mockMvc.perform(get("/api/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateUsers() throws Exception {
		UserDTO userDTO = new UserDTO(
				1, "teste", "1234", "teste@teste.com", "teste", "teste", format.parse("2000-01-01"), "BASIC", null
				);
		when(service.createUser(userDTO)).thenReturn(ResponseEntity.ok(userDTO));
		mockMvc
				.perform(
						post("/api/users")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testCreateUsersWithInvalidData() throws Exception {
		when(service.createUser(null)).thenReturn(ResponseEntity.badRequest().build());
		mockMvc.perform(post("/api/users"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testUpdateUsers() throws Exception {
		UserDTO userDTO = new UserDTO(
				1, "teste", "1234", "teste@teste.com", "teste", "teste", format.parse("2000-01-01"), "BASIC", null
				);
		when(service.updateUser(1, userDTO)).thenReturn(ResponseEntity.ok(userDTO));
		mockMvc
				.perform(
						put("/api/users/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteUsers() throws Exception {
		when(service.deleteUser(1)).thenReturn(ResponseEntity.ok().build());
		mockMvc.perform(delete("/api/users/1"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void testDeleteUsersWithoutExists() throws Exception {
		when(service.deleteUser(1)).thenReturn(ResponseEntity.notFound().build());
		mockMvc.perform(delete("/api/users/1"))
				.andExpect(status().isNotFound());
	}
}
