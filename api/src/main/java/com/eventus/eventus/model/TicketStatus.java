package com.eventus.eventus.model;

public enum TicketStatus {
	ACTIVE("Active"),
	CANCELLED("Cancelled"),
	PEDING("Pending"),
	EXPIRED("Expired");
	String status;
	TicketStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
}
