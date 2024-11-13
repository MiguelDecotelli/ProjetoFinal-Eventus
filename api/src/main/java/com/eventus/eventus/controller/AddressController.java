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

@RestController
@RequestMapping("/api/address")
public class AddressController {
	@Autowired
	AddressService service;
	@GetMapping
	public ResponseEntity<List<AddressModel>> getAllAddress(){
		return service.readAllAddress();
	}
	@GetMapping("/{id}")
	public ResponseEntity<AddressModel> getAddressById(@PathVariable int id){
		return service.readAddressById(id);
	}
	@PostMapping
	public ResponseEntity<AddressModel> postAddress(@RequestBody AddressModel data){
		return service.createAddress(data);
	}
	@PutMapping("/{id}")
	public ResponseEntity<AddressModel> putAddress(@PathVariable int id, @RequestBody AddressModel data){
		return service.updateAddress(id, data);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressModel> deleteAddress(@PathVariable int id){
		return service.deleteAddress(id);
	}
}
