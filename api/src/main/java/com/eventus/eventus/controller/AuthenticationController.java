package com.eventus.eventus.controller;

import com.eventus.eventus.dto.AuthenticationDTO;
import com.eventus.eventus.dto.RegistrationDTO;
import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.service.AuthenticationService;
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
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody AuthenticationDTO data){
    return service.login(data);
  }
  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@RequestBody RegistrationDTO data){
    return service.register(data);
  }
}
