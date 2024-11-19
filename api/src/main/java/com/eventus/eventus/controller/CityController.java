package com.eventus.eventus.controller;

import java.util.List;

import com.eventus.eventus.dto.CityDTO;
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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/cities")
public class CityController {
	@Autowired
	private CityService service;

	@Operation(summary = "", description = "")
	@GetMapping
	public ResponseEntity<List<CityModel>> getAllCities() {
		return service.readAllCities();
	}

	@Operation(summary = "Retorna uma lista com todas as cidades cadastradas no banco de dados", description = "Busca no banco de dados todas as cidade cadastradas e retorna elas em uma lista")
	@GetMapping("/{id}")
	public ResponseEntity<CityModel> getCityById(@PathVariable int id) {
		return service.readCityById(id);
	}

	@Operation(summary = "Retorna os dados da cidade", description = "Retorna os dados da cidade requisitada via URI")
	@PostMapping
	public ResponseEntity<CityModel> createCity(@RequestBody CityDTO data) {
		return service.createCity(data);
	}

	@Operation(summary = "Atualiza as informações da cidade", description = "Atualiza as informações da cidade requisitada via URI")
	@PutMapping("/{id}")
	public ResponseEntity<CityModel> updateCity(@PathVariable int id, @RequestBody CityDTO data) {
		return service.updateCity(id, data);
	}

	@Operation(summary = "Deleta a cidade requisitada", description = "Deleta no banco de dados a cidade requisitada via URI")
	@DeleteMapping("/{id}")
	public ResponseEntity<CityModel> deleteCity(@PathVariable int id) {
		return service.deleteCity(id);
	}
}
