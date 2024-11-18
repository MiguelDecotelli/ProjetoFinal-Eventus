package com.eventus.eventus.controller;

import com.eventus.eventus.dto.EventsDTO;
import com.eventus.eventus.dto.NewEventsDTO;
import com.eventus.eventus.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping
    public ResponseEntity<List <EventsDTO>> getAllEvents() {
        return eventsService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventsDTO> getEventsById (@PathVariable int id){
        return eventsService.getEventsById(id);
    }

    @PostMapping
    public ResponseEntity<EventsDTO> createEvents (@RequestBody NewEventsDTO eventsDTO){
        return eventsService.createEvents(eventsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventsDTO> updateEvents (@PathVariable int id, @RequestBody NewEventsDTO eventsDTO){
        return eventsService.updateEvents(id, eventsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEvents(@PathVariable int id){
        return eventsService.deleteEvents(id);
    }
}
