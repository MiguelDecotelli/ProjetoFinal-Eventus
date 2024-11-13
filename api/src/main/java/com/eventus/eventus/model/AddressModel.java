package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EventAddress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EventAddress_id")
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
	@JoinColumn(name = "city_id")
	private CityModel city;
}
