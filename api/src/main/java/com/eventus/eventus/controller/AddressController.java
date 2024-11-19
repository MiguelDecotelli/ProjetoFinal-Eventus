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

import com.eventus.eventus.model.AddressModel;
import com.eventus.eventus.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	@Autowired
	AddressService service;

	@Operation(summary = "Obter todos os endereços cadastrados no banco de dados", description = "Retorna uma lista de endereços cadastrados no banco de dados")
	@GetMapping
	public ResponseEntity<List<AddressModel>> getAllAddress() {
		return service.readAllAddress();
	}

	@Operation(summary = "Obter endereço cadastrados no banco de dados via ID", description = "Retorna um endereço cadastrado no banco de dados, caso o identificador seja valido")
	@GetMapping("/{id}")
	public ResponseEntity<AddressModel> getAddressById(@PathVariable int id) {
		return service.readAddressById(id);
	}

	@Operation(summary = "Salva um novo endereço no banco de dados", description = "Recebe as informações de um endereço, faz a validação e retorna ele com seu ID")
	@PostMapping
	public ResponseEntity<AddressModel> postAddress(@RequestBody AddressModel data) {
		return service.createAddress(data);
	}

	@Operation(summary = "Atualiza as informações de um endereço existente no banco de dados", description = "Requer o endereço com todas suas informações novas, inclusive as informações que não irão sofrer mudanças, para atualizar no banco de dados")
	@PutMapping("/{id}")
	public ResponseEntity<AddressModel> putAddress(@PathVariable int id, @RequestBody AddressModel data) {
		return service.updateAddress(id, data);
	}

	@Operation(summary = "Exclue um endereço salvo no banco de dados", description = "Retorna se foi possível a exclusão do endereço")
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressModel> deleteAddress(@PathVariable int id) {
		return service.deleteAddress(id);
	}
}
