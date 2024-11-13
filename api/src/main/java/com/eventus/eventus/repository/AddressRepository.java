package com.eventus.eventus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventus.eventus.model.AddressModel;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer>{ }
