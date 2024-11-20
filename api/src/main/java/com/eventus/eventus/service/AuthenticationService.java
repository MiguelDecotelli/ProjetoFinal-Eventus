package com.eventus.eventus.service;

import com.eventus.eventus.dto.AuthenticationDTO;
import com.eventus.eventus.dto.CityDTO;
import com.eventus.eventus.dto.RegistrationDTO;
import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.model.CityModel;
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
import org.springframework.security.core.userdetails.UserDetails;
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
      UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();
      UserModel user = repository.findByUsernameAndPassword(authenticatedUser.getUsername(), authenticatedUser.getPassword());
      return ResponseEntity.ok(jwtTokenProvider.generateToken(user));
    }catch(AuthenticationException e){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
  }
  public ResponseEntity<UserDTO> register(RegistrationDTO data){
    UserModel userModel = new UserModel();
    userModel.setUsername(data.getUsername());
    userModel.setLastname(data.getLastname());
    userModel.setEmail(data.getEmail());
    userModel.setBirthday(data.getBirthday());
    userModel.setName(data.getName());
    userModel.setRole(UserRole.BASIC);
    userModel.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
		userModel.setCity(null);
    try {
      UserModel savedUser = repository.save(userModel);
			CityDTO city = savedUser.getCity() != null ? convertCityModelToCityDTO(savedUser.getCity()) : null ;
			UserDTO user = new UserDTO(
					savedUser.getId(),
					savedUser.getUsername(), 
					savedUser.getPassword(), 
					savedUser.getEmail(), 
					savedUser.getName(), 
					savedUser.getLastname(), 
					savedUser.getBirthday(), 
					savedUser.getRole().getRole(), 
					city
				);
      return ResponseEntity.ok(user);
    } catch (DataAccessException e){
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
	private CityDTO convertCityModelToCityDTO(CityModel model){
		return new CityDTO(model.getName(), model.getState());
	}
}
