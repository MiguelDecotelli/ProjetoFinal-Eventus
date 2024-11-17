package com.eventus.eventus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "cep")
	private String cep;
	@Column(name = "street")
	private String street;
	@Column(name = "street_number")
	private String streetNumber;
	@Column(name = "complement")
	private String complement;
	@Column(name = "description")
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Cities_id")
	private CityModel city;

	// Constructors
	public AddressModel() {
	}

	public AddressModel(int id, String cep, String street, String streetNumber, String complement, String description,
			CityModel city) {
		this.id = id;
		this.cep = cep;
		this.street = street;
		this.streetNumber = streetNumber;
		this.description = description;
		this.complement = complement;
		this.city = city;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CityModel getCity() {
		return city;
	}

	public void setCity(CityModel city) {
		this.city = city;
	}
}
