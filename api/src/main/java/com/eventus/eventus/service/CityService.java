package com.eventus.eventus.service;

import java.util.List;
import java.util.Optional;

import com.eventus.eventus.dto.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventus.eventus.model.CityModel;
import com.eventus.eventus.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository repository;

	public ResponseEntity<CityModel> createCity(CityDTO data) {
		CityModel newCity = new CityModel();
		newCity.setName(data.name());
		newCity.setState(data.state());
		try {
			CityModel city = repository.save(newCity);
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

	public ResponseEntity<CityModel> updateCity(int id, CityDTO data) {
		Optional<CityModel> city = repository.findById(id);
		if (city.isEmpty()) ResponseEntity.notFound().build();
		city.get().setName(data.name());
		city.get().setState(data.state());
		try {
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
