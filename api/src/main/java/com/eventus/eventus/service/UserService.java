package com.eventus.eventus.service;

import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.model.UserModel;
import com.eventus.eventus.model.UserRole;
import com.eventus.eventus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = repository.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(users);
    }
    public ResponseEntity<UserDTO> getUserById(int id){
        try {
            Optional<UserModel> userOption = repository.findById(id);
            if (userOption.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(convertToDTO(userOption.get()));

        } catch(DataAccessException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO){
        UserModel userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        userModel.setName(userDTO.getName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setBirthday(userDTO.getBirthday());
        userModel.setLastname(userDTO.getLastname());
        try {
            repository.save(userModel);
            return ResponseEntity.ok(convertToDTO(userModel));
        } catch(DataAccessException e){
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<UserDTO> updateUser(int id, UserDTO userDTO){
        Optional<UserModel> userOption = repository.findById(id);
        if (userOption.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            UserModel userModel = new UserModel();
            userModel.setUsername(userDTO.getUsername());
            userModel.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            userModel.setName(userDTO.getName());
            userModel.setEmail(userDTO.getEmail());
            userModel.setBirthday(userDTO.getBirthday());
            userModel.setLastname(userDTO.getLastname());
            repository.save(userModel);
            return ResponseEntity.ok(convertToDTO(userModel));
        } catch (DataAccessException e){
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<Object> deleteUser(int id){
        try {
            Optional<UserModel> userOption = repository.findById(id);
            if (userOption.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
    private UserDTO convertToDTO(UserModel model){
        return new UserDTO(
					model.getId(),
					model.getUsername(),
					model.getPassword(),
					model.getEmail(),
					model.getName(),
					model.getLastname(),
					model.getBirthday(),
					model.getRole().getRole(),
					model.getCity()
				);
    }
}
