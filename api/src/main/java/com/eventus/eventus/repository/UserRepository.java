package com.eventus.eventus.repository;

import com.eventus.eventus.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> { }
