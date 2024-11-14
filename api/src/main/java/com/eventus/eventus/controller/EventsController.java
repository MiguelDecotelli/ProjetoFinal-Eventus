package com.eventus.eventus.controller;

import com.eventus.eventus.dto.EventsDTO;
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
        EventsDTO eventsDTO = eventsService.getEventsById(id).getBody();
        return eventsDTO != null ? ResponseEntity.ok(eventsDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public EventsDTO createEvents (@RequestBody EventsDTO eventsDTO){
        return eventsService.createEvents(eventsDTO).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventsDTO> updateEvents (@PathVariable int id, @RequestBody EventsDTO eventsDTO){
        EventsDTO updateEvents = eventsService.updateEvents(id, eventsDTO).getBody();
        return updateEvents != null ? ResponseEntity.ok(updateEvents) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvents(@PathVariable int id){
        // TODO: Make response to this
        eventsService.deleteEvents(id);
        return ResponseEntity.noContent().build();
    }
}
