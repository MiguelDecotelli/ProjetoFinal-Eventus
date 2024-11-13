package com.eventus.eventus.repository;

import com.eventus.eventus.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
  UserDetails findByUsername(String username);
  UserModel findByUsernameAndPassword(String username, String password);
}