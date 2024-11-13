package com.eventus.eventus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventus.eventus.model.CityModel;

public interface CityRepository extends JpaRepository<CityModel, Integer>{ }
