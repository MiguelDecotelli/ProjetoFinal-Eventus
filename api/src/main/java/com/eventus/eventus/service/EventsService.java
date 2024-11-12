package com.eventus.eventus.service;

import com.eventus.eventus.dto.EventsDTO;
import com.eventus.eventus.model.EventsModel;
import com.eventus.eventus.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventsService {
    @Autowired
    EventsRepository repository;
    public ResponseEntity<List<EventsDTO>> getAllEvents(){
        try {
            List<EventsDTO> events = repository
                    .findAll()
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(events);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<EventsDTO> getEventsById(int id){
        try {
            Optional<EventsModel> events = repository.findById(id);
            if (events.isEmpty()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(convertToDTO(events.get()));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<EventsDTO> createEvents(EventsDTO eventsDTO){
        EventsModel eventsModel = new EventsModel();
        eventsModel.setId(eventsDTO.getId());
        eventsModel.setName(eventsDTO.getName());
        eventsModel.setInitialDate(eventsDTO.getInitialDate());
        eventsModel.setFinalDate(eventsDTO.getFinalDate());
        eventsModel.setDescription(eventsDTO.getDescription());
        eventsModel.setEventImage(eventsDTO.getEventImage());
        eventsModel.setEventStatus(eventsDTO.getEventStatus());
        eventsModel.setEventAddress(eventsDTO.getEventAddress());

        try {
            var event = repository.save(eventsModel);
            return ResponseEntity.ok(convertToDTO(event));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<EventsDTO> updateEvents(int id, EventsDTO eventsDTO){
        Optional<EventsModel> eventsOption = repository.findById(id);
        if(eventsOption.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        EventsModel eventsModel = new EventsModel();
        eventsModel.setName(eventsDTO.getName());
        eventsModel.setInitialDate(eventsDTO.getInitialDate());
        eventsModel.setFinalDate(eventsDTO.getFinalDate());
        eventsModel.setDescription(eventsDTO.getDescription());
        eventsModel.setEventImage(eventsDTO.getEventImage());
        eventsModel.setEventStatus(eventsDTO.getEventStatus());
        eventsModel.setEventAddress(eventsDTO.getEventAddress());

        try {
            var event = repository.save(eventsModel);
            return ResponseEntity.ok(convertToDTO(event));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity deleteEvents(int id){
        Optional<EventsModel> eventsOption = repository.findById(id);
        if(eventsOption.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    private EventsDTO convertToDTO(EventsModel eventsModel){
        EventsDTO eventsDTO = new EventsDTO();
        eventsDTO.setId(eventsModel.getId());
        eventsDTO.setName(eventsModel.getName());
        eventsDTO.setInitialDate(eventsModel.getInitialDate());
        eventsDTO.setFinalDate(eventsModel.getFinalDate());
        eventsDTO.setEventImage(eventsModel.getEventImage());
        eventsDTO.setEventStatus(eventsModel.getEventStatus());
        eventsDTO.setEventAddress(eventsModel.getEventAddress());
        return eventsDTO;
    }
}
