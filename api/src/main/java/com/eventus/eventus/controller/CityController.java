package com.eventus.eventus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventus.eventus.model.CityModel;
import com.eventus.eventus.service.CityService;

@RestController
@RequestMapping("/api/cities")
public class CityController {
	@Autowired
	private CityService service;

	@GetMapping
	public ResponseEntity<List<CityModel>> getAllCities(){
		return service.readAllCities();
	}
	@GetMapping("/{id}")
	public ResponseEntity<CityModel> getCityById(@PathVariable int id){
		return service.readCityById(id);
	}
	@PostMapping
	public ResponseEntity<CityModel> createCity(@RequestBody CityModel data){
		return service.createCity(data);
	}
	@PutMapping("/{id}")
	public ResponseEntity<CityModel> updateCity(@PathVariable int id, @RequestBody CityModel data){
		return service.updateCity(id, data);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CityModel> deleteCity(@PathVariable int id){
		return service.deleteCity(id);
	}
}
