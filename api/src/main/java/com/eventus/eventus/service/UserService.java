package com.eventus.eventus.service;

import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.model.UserModel;
import com.eventus.eventus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<UserDTO> getAllUsers(){
        return userRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    // TODO: Change type
    public UserDTO getUserById(int id){
        Optional<UserModel> user= userRepository.findById(id);
        return user.map(this::convertToDTO).orElse(null);
    }
    public UserDTO createUser(UserDTO userDTO){
        // TODO: Handle Erros
        UserModel userModel = new UserModel();
        userModel.setUserUsername(userDTO.getUserUsername());
        userModel.setUserName(userDTO.getUserName());
        userModel.setUserEmail(userDTO.getUserEmail());
        userModel.setUserBirthday(userDTO.getUserBirthday());
        userModel.setUserLastname(userDTO.getUserLastname());
        userRepository.save(userModel);
        return convertToDTO(userModel);
    }
    public UserDTO updateUser(int id, UserDTO userDTO){
        // TODO: Handle if user has permissions
        Optional<UserModel> userOption = userRepository.findById(id);
        if(!userOption.isPresent()){
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setUserUsername(userDTO.getUserUsername());
        userModel.setUserName(userDTO.getUserName());
        userModel.setUserEmail(userDTO.getUserEmail());
        userModel.setUserBirthday(userDTO.getUserBirthday());
        userModel.setUserLastname(userDTO.getUserLastname());
        userRepository.save(userModel);
        return convertToDTO(userModel);
    }
    public void deleteUser(int id){
        // TODO: Handle if user exists
        // TODO: Handle if user has permissions
        // TODO: Handle errors
        userRepository.deleteById(id);
    }
    private UserDTO convertToDTO(UserModel userModel){
        // TODO: Handle if user has permissions
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userModel.getUserId());
        userDTO.setUserName(userModel.getUserName());
        userDTO.setUserUsername(userModel.getUserUsername());
        userDTO.setUserLastname(userModel.getUserLastname());
        userDTO.setUserEmail(userModel.getUserEmail());
        return userDTO;
    }
}
