package com.eventus.eventus.controller;

import com.eventus.eventus.dto.AuthenticationDTO;
import com.eventus.eventus.dto.RegistrationDTO;
import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.dto.UserLogedDTO;
import com.eventus.eventus.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	@Autowired
	AuthenticationService service;

	@Operation(summary = "Retorna o token JWT se os dados do usuario estiverem corretos", description = "")
	@PostMapping("/login")
	public ResponseEntity<UserLogedDTO> login(@RequestBody AuthenticationDTO data) {
		return service.login(data);
	}

	@Operation(summary = "Executa a criação de um novo usuario com ROLE fixa em basico", description = "")
	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody RegistrationDTO data) {
		return service.register(data);
	}
}
