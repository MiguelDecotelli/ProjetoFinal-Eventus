package com.eventus.eventus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cities")
public class CityModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "state", nullable = false, unique = false)
	private String state;

	// Constructors
	public CityModel(){}
	public CityModel(int id, String name, String state){
		this.id = id;
		this.name = name;
		this.state = state;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
