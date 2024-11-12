package com.eventus.eventus.dto;

import com.eventus.eventus.model.EventsStatus;
import lombok.Data;

@Data
public class EventsDTO {
    private int id;
    private String name;
    private String initialDate;
    private String finalDate;
    private String description;
    private String eventImage;
    private EventsStatus eventStatus;
    private String eventAddress;
}
