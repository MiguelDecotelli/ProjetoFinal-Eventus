package com.eventus.eventus.service;

import com.eventus.eventus.dto.EventsDTO;
import com.eventus.eventus.dto.NewEventsDTO;
import com.eventus.eventus.model.AddressModel;
import com.eventus.eventus.model.EventsModel;
import com.eventus.eventus.repository.AddressRepository;
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
	@Autowired
	AddressRepository addressRepository;

	public ResponseEntity<List<EventsDTO>> getAllEvents() {
		try {
			List<EventsDTO> events = repository
					.findAll()
					.stream()
					.map(this::convertToDTO)
					.collect(Collectors.toList());
			return ResponseEntity.ok(events);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<EventsDTO> getEventsById(int id) {
		try {
			Optional<EventsModel> events = repository.findById(id);
			if (events.isEmpty())
				return ResponseEntity.notFound().build();
			return ResponseEntity.ok(convertToDTO(events.get()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<EventsDTO> createEvents(NewEventsDTO eventsDTO){
        EventsModel eventsModel = new EventsModel();
        eventsModel.setId(eventsDTO.getId());
        eventsModel.setName(eventsDTO.getName());
        eventsModel.setInitialDate(eventsDTO.getInitialDate());
        eventsModel.setFinalDate(eventsDTO.getFinalDate());
        eventsModel.setDescription(eventsDTO.getDescription());
        eventsModel.setEventImage(eventsDTO.getEventImage());
        eventsModel.setEventStatus(eventsDTO.getEventStatus());
        eventsModel.setEventAddress(findAddressById(eventsDTO.getEventAddress()));

        try {
            var event = repository.save(eventsModel);
            return ResponseEntity.ok(convertToDTO(event));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

	public ResponseEntity<EventsDTO> updateEvents(int id, NewEventsDTO eventsDTO) {
		Optional<EventsModel> eventsOption = repository.findById(id);
		if (eventsOption.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		eventsOption.get().setName(eventsDTO.getName());
		eventsOption.get().setInitialDate(eventsDTO.getInitialDate());
		eventsOption.get().setFinalDate(eventsDTO.getFinalDate());
		eventsOption.get().setDescription(eventsDTO.getDescription());
		eventsOption.get().setEventImage(eventsDTO.getEventImage());
		eventsOption.get().setEventStatus(eventsDTO.getEventStatus());
		eventsOption.get().setEventAddress(findAddressById(eventsDTO.getEventAddress()));

		try {
			EventsModel event = repository.save(eventsOption.get());
			return ResponseEntity.ok(convertToDTO(event));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Object> deleteEvents(int id) {
		Optional<EventsModel> eventsOption = repository.findById(id);
		if (eventsOption.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	private AddressModel findAddressById(int id) {
		return addressRepository.findById(id).get();
	}

	private EventsDTO convertToDTO(EventsModel model) {
		return new EventsDTO(
				model.getId(),
				model.getName(),
				model.getInitialDate(),
				model.getFinalDate(),
				model.getDescription(),
				model.getEventImage(),
				model.getEventStatus(),
				model.getEventAddress()
			);
	}
}
