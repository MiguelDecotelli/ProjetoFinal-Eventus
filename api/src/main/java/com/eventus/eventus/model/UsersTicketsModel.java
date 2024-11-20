package com.eventus.eventus.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="UsersTickets")
public class UsersTicketsModel {
	@Id
	@Column(name="UsersTickets_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "Users_id", unique = false, nullable = false)
	private UserModel user;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "Tickets_id", unique = false, nullable = false)
	private TicketModel ticket;
	@Column(name = "ticket_status", unique = false, nullable = false)
	private TicketStatus status;

	// Constructors 
	public UsersTicketsModel(int id, UserModel user, TicketModel ticket){
		this.id = id;
		this.user = user;
		this.ticket = ticket;
		this.status = TicketStatus.PEDING;
	}
	public UsersTicketsModel(){ this.status = TicketStatus.PEDING; }

	// Ticket Methods
	public void cancelTicket(){
		this.status = TicketStatus.CANCELLED;
	}
	public void expiresTicket(){
		this.status = TicketStatus.EXPIRED;
	}
	public void paysTicket(){
		this.status = TicketStatus.ACTIVE;
	}
	public String getTicketStatus(){
		return this.status.getStatus();
	}

	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public TicketStatus getStatus() {
		return status;
	}
	public TicketModel getTicket() {
		return ticket;
	}
	public void setTicket(TicketModel ticket) {
		this.ticket = ticket;
	}
}
