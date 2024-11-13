package com.eventus.eventus.repository;

import com.eventus.eventus.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
  public UserDetails findByUsername(String username);
  public UserDetails findByEmail(String email);
}