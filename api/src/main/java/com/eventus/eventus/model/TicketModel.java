package com.eventus.eventus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {
	@Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "ticket_name")
	private String name;
	@Column(name = "ticket_description")
	private String description;
	@Column(name = "ticket_amount")
	private int amount;

	public TicketModel(String name, String description, int amount){
		this.name = name;
		this.description = description;
		this.amount = amount;
	}
}
