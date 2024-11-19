package com.eventus.eventus.dto;

public record TicketDTO(int id,String name,String description,int amount, EventsDTO event){
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getAmount() {
		return amount;
	}
	public EventsDTO getEvent() {
		return event;
	}
}
