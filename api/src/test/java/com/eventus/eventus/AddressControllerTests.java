package com.eventus.eventus;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eventus.eventus.controller.AddressController;
import com.eventus.eventus.model.AddressModel;
import com.eventus.eventus.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WebMvcTest(AddressController.class)
public class AddressControllerTests {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	@MockBean
	private AddressService service;
	@InjectMocks
	private AddressController controller;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testGetAllAddress() throws Exception {
		AddressModel address = new AddressModel(
				1, "97015800", "Rua Dos Testes", "111", "casa dos testes", "local do qual os testes acontecem", null);
		when(service.readAllAddress()).thenReturn(ResponseEntity.ok(List.of(address)));
		mockMvc.perform(get("/api/address"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testAddressById() throws Exception {
		AddressModel address = new AddressModel(
				1, "97015800", "Rua Dos Testes", "111", "casa dos testes", "local do qual os testes acontecem", null);
		when(service.readAddressById(1)).thenReturn(ResponseEntity.ok(address));
		mockMvc.perform(get("/api/address/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testCreateAddress() throws Exception {
		AddressModel address = new AddressModel(
				1, "97015800", "Rua Dos Testes", "111", "casa dos testes", "local do qual os testes acontecem", null);
		when(service.createAddress(address)).thenReturn(ResponseEntity.ok(address));
		mockMvc.perform(post("/api/address")
				.content(objectMapper.writeValueAsString(address))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testCreateAddressWithInvalidData() throws Exception {
		AddressModel address = null;
		when(service.createAddress(address)).thenReturn(ResponseEntity.badRequest().build());
		mockMvc.perform(post("/api/address")
				.content(objectMapper.writeValueAsString(address))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testUpdateAddress() throws Exception {
		AddressModel address = new AddressModel(
				1, "97015800", "Rua Dos Testes", "111", "casa dos testes", "local do qual os testes acontecem", null);
		when(service.updateAddress(1, address)).thenReturn(ResponseEntity.ok(address));
		mockMvc.perform(put("/api/address/1")
				.content(objectMapper.writeValueAsString(address))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testDeleteAddress() throws Exception {
		when(service.deleteAddress(1)).thenReturn(ResponseEntity.ok().build());
		mockMvc.perform(delete("/api/address/1")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testDeleteAddressWithoutExists() throws Exception {
		when(service.deleteAddress(1)).thenReturn(ResponseEntity.notFound().build());
		mockMvc.perform(delete("/api/address/1")).andExpect(status().isNotFound());
	}
}
