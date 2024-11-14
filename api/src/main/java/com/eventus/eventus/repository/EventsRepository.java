package com.eventus.eventus.repository;

import com.eventus.eventus.model.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<EventsModel, Integer> {
}
