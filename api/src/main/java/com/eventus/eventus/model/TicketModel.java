package com.eventus.eventus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tickets")
public class TicketModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "amount")
	private int amount;
	@Column(name = "value")
	private float value;
	@ManyToOne
	@JoinColumn(name="Events_id")
	private EventsModel event;
	// Constructors
	public TicketModel(){}
	public TicketModel(String name, String description, int amount, EventsModel event, float value){
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.event = event;
		this.value = value;
	}
	public TicketModel(int id, String name, String description, int amount, EventsModel event, float value){
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.event = event;
		this.value = value;
	}

	// Getters and Setters
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setEvent(EventsModel event) {
		this.event = event;
	}
	public EventsModel getEvent() {
		return event;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}

}
