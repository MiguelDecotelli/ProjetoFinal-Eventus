package com.eventus.eventus.service;

import com.eventus.eventus.dto.AuthenticationDTO;
import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.model.UserModel;
import com.eventus.eventus.model.UserRole;
import com.eventus.eventus.repository.UserRepository;
import com.eventus.eventus.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  @Autowired
  UserRepository repository;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  public ResponseEntity<String> login(AuthenticationDTO data){
    try{
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword())
      );
      UserModel user = (UserModel) authentication.getPrincipal();
      return ResponseEntity.ok(jwtTokenProvider.generateToken(user));
    }catch(AuthenticationException e){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
  }
  public ResponseEntity<UserDTO> register(UserDTO data){
    UserModel userModel = new UserModel();
    userModel.setUsername(data.getUsername());
    userModel.setLastname(data.getLastname());
    userModel.setEmail(data.getEmail());
    userModel.setBirthday(data.getBirthday());
    userModel.setName(data.getName());
    userModel.setRole(UserRole.BASIC);
    userModel.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
    try {
      repository.save(userModel);
      data.setRole("BASIC");
      return ResponseEntity.ok(data);
    } catch (DataAccessException e){
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}