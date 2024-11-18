package com.eventus.eventus.dto;

import com.eventus.eventus.model.EventsModel;

public record TicketDTO(int id,String name,String description,int amount, EventsModel event){
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
	public EventsModel getEvent() {
		return event;
	}
}
