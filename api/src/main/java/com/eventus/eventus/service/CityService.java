package com.eventus.eventus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventus.eventus.model.CityModel;
import com.eventus.eventus.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository repository;

	public ResponseEntity<CityModel> createCity(CityModel data) {
		try {
			CityModel city = repository.save(data);
			return ResponseEntity.ok(city);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<List<CityModel>> readAllCities() {
		try {
			List<CityModel> allCities = repository.findAll();
			return ResponseEntity.ok(allCities);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<CityModel> readCityById(int id) {
		Optional<CityModel> city = repository.findById(id);
		if (city.isEmpty()) ResponseEntity.notFound().build();
		try {
			return ResponseEntity.ok(city.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<CityModel> updateCity(int id, CityModel data) {
		Optional<CityModel> city = repository.findById(id);
		if (city.isEmpty()) ResponseEntity.notFound().build();
		try {
			city.get().setName(data.getName());
			city.get().setState(data.getState());
			CityModel updatedCity = repository.save(city.get());
			return ResponseEntity.ok(updatedCity);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<CityModel> deleteCity(int id) {
		Optional<CityModel> city = repository.findById(id);
		if (city.isEmpty()) ResponseEntity.notFound().build();
		try {
			repository.deleteById(id);
			return ResponseEntity.ok(city.get());

		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
