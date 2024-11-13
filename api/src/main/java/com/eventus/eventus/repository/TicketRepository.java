package com.eventus.eventus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventus.eventus.model.TicketModel;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Integer>{ }
