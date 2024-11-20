package com.eventus.eventus.model;

import java.util.Set;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Events")
public class EventsModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "initial_date")
	private Date initialDate;

	@Column(name = "final_date")
	private Date finalDate;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String eventImage;

	@Column(name = "status")
	private EventsStatus eventStatus;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "Address_id")
	private AddressModel eventAddress;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "Users_id")
	private Set<UserModel> admins;

	public EventsModel(){}
	public EventsModel(int id, String name, Date initialDate, Date finalDate, String description, String eventImage, EventsStatus eventStatus, AddressModel eventAddress, Set<UserModel> admins) {
		this.id = id;
		this.name = name;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.description = description;
		this.eventImage = eventImage;
		this.eventStatus = eventStatus;
		this.eventAddress = eventAddress;
		this.admins = admins;
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

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventImage() {
		return eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}

	public EventsStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventsStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	public AddressModel getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(AddressModel eventAddress) {
		this.eventAddress = eventAddress;
	}

	public Set<UserModel> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<UserModel> admins) {
		this.admins = admins;
	}
}
