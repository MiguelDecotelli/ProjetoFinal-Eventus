package com.eventus.eventus.controller;

import com.eventus.eventus.dto.AuthenticationDTO;
import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.model.UserModel;
import com.eventus.eventus.security.JwtTokenProvider;
import com.eventus.eventus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserService userService;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody AuthenticationDTO data){
    try{
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword())
      );
      UserModel user = (UserModel) authentication.getPrincipal();
      return ResponseEntity.ok(jwtTokenProvider.generateToken(user));
    }catch(AuthenticationException e){
      throw new RuntimeException("Invalid Credentials");
    }
  }
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody UserDTO data){
    data.setRole("basic");
    return ResponseEntity.ok(userService.createUser(data));
  }
}
