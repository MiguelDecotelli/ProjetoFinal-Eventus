package com.eventus.eventus.dto;

public record TicketDTO(int id,String name,String description,int amount, int event){
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
	public int getEvent() {
		return event;
	}
}
