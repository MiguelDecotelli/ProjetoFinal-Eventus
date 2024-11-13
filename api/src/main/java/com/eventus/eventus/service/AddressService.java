package com.eventus.eventus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventus.eventus.model.AddressModel;
import com.eventus.eventus.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;

	public ResponseEntity<AddressModel> createAddress(AddressModel data){
		try {
			AddressModel address = repository.save(data);
			return ResponseEntity.ok(address);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	public ResponseEntity<List<AddressModel>> readAllAddress(){
		try {
			List<AddressModel> allAddress = repository.findAll();
			return ResponseEntity.ok(allAddress);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	public ResponseEntity<AddressModel> readAddressById(int id){
		try {
			Optional<AddressModel> address = repository.findById(id);
			if(address.isEmpty()) return ResponseEntity.notFound().build();
			return ResponseEntity.ok(address.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	public ResponseEntity<AddressModel> updateAddress(int id, AddressModel data){
			Optional<AddressModel> address = repository.findById(id);
			if(address.isEmpty()) return ResponseEntity.notFound().build();
			try {
				address.get().setCep(data.getCep());
				address.get().setStreet(data.getStreet());
				address.get().setStreetNumber(data.getStreetNumber());
				address.get().setComplement(data.getComplement());
				address.get().setDescription(data.getDescription());
				var updatedAddress = repository.save(address.get());
				return ResponseEntity.ok(updatedAddress);
			} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
			}
	}
	public ResponseEntity<AddressModel> deleteAddress(int id){
			Optional<AddressModel> address = repository.findById(id);
			if(address.isEmpty()) return ResponseEntity.notFound().build();
			try {
				repository.deleteById(id);
				return ResponseEntity.ok(address.get());
			} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
			}
	}
}
