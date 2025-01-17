package com.eventus.eventus.dto;

import java.util.Date;

import com.eventus.eventus.model.EventsStatus;

public record NewEventsDTO(int id, String name, Date initialDate, Date finalDate,
		String description, String eventImage, EventsStatus eventStatus,
		int eventAddress){

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public String getDescription() {
		return description;
	}
	public String getEventImage() {
		return eventImage;
	}
	public EventsStatus getEventStatus() {
		return eventStatus;
	}
	public int getEventAddress() {
		return eventAddress;
	}
}
