package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityModel {
	@Id
	@Column(name = "city_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "state", nullable = false, unique = false)
	private String state;
}
