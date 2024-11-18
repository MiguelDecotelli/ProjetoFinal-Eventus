package com.eventus.eventus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventus.eventus.model.UsersTicketsModel;

@Repository
public interface UsersTicketsRepository extends JpaRepository<UsersTicketsModel, Integer>{
	List<UsersTicketsModel> findAllByTicketId(int ticketId);
	List<UsersTicketsModel> findAllByUserId(int userId);
	Optional<UsersTicketsModel> findByUserIdAndTicketId(int userId, int ticketId);
}
