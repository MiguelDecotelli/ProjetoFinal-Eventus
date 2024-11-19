package com.eventus.eventus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventus.eventus.repository.TicketRepository;
import com.eventus.eventus.repository.UserRepository;
import com.eventus.eventus.repository.UsersTicketsRepository;
import com.eventus.eventus.dto.CityDTO;
import com.eventus.eventus.dto.EventsDTO;
import com.eventus.eventus.dto.TicketDTO;
import com.eventus.eventus.dto.TicketUsersDTO;
import com.eventus.eventus.dto.UserDTO;
import com.eventus.eventus.dto.UserTicketDTO;
import com.eventus.eventus.dto.UserTicketsDTO;
import com.eventus.eventus.model.CityModel;
import com.eventus.eventus.model.EventsModel;
import com.eventus.eventus.model.TicketModel;
import com.eventus.eventus.model.UserModel;
import com.eventus.eventus.model.UsersTicketsModel;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class UsersTicketsService {
	@Autowired
	private UsersTicketsRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;

	public ResponseEntity<TicketUsersDTO> getAllUsersByTicket(int ticketId) {
		try {
			Optional<TicketModel> ticket = ticketRepository.findById(ticketId);
			if (ticket.isEmpty())
				return ResponseEntity.notFound().build();
			List<UsersTicketsModel> userTickets = repository.findAllByTicketId(ticketId);
			Set<UserDTO> usersSet = Set.copyOf(userTickets
					.stream()
					.map(this::getAllUsers)
					.map(this::convertUserModelToUserDto)
					.toList()
			);
			TicketUsersDTO ticketUsersDTO = new TicketUsersDTO(convertTicketModelToTicketDto(ticket.get()), usersSet);
			return ResponseEntity.ok(ticketUsersDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<UserTicketsDTO> getAllTicketsByUser(int userId) {
		try {
			Optional<UserModel> user = userRepository.findById(userId);
			if (user.isEmpty())
				return ResponseEntity.notFound().build();
			List<UsersTicketsModel> allTickets = repository.findAllByUserId(userId);
			Set<TicketDTO> ticketsSet = Set.copyOf(allTickets
					.stream()
					.map(this::getAllTickets)
					.map(this::convertTicketModelToTicketDto)
					.toList()
			);
			UserTicketsDTO userTicketsDTO = new UserTicketsDTO(convertUserModelToUserDto(user.get()), ticketsSet);
			return ResponseEntity.ok(userTicketsDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<UsersTicketsModel> createNewUserTicket(int userId, int ticketId) {
		try {
			Optional<UserModel> user = userRepository.findById(userId);
			if (user.isEmpty())
				return ResponseEntity.notFound().build();
			Optional<TicketModel> ticket = ticketRepository.findById(ticketId);
			if (ticket.isEmpty())
				return ResponseEntity.notFound().build();
			UsersTicketsModel usersTicketsModel = new UsersTicketsModel();
			usersTicketsModel.setUser(user.get());
			usersTicketsModel.setTicket(ticket.get());
			UsersTicketsModel data = repository.save(usersTicketsModel);
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Object> cancelTicket(int userId, int ticketId) {
		try {
			Optional<UsersTicketsModel> ticket = repository.findByUserIdAndTicketId(userId, ticketId);
			if (ticket.isEmpty())
				return ResponseEntity.notFound().build();
			ticket.get().cancelTicket();
			repository.save(ticket.get());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Object> confirmPaymentTicket(int id) {
		try {
			Optional<UsersTicketsModel> ticket = repository.findById(id);
			if (ticket.isEmpty())
				return ResponseEntity.notFound().build();
			ticket.get().paysTicket();
			repository.save(ticket.get());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Object> expiresPaymentTicket(int id) {
		try {
			Optional<UsersTicketsModel> ticket = repository.findById(id);
			if (ticket.isEmpty())
				return ResponseEntity.notFound().build();
			ticket.get().expiresTicket();
			repository.save(ticket.get());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	public ResponseEntity<UserTicketDTO> getUserTicket(int userId, int ticketId){
		try {
			Optional<UsersTicketsModel> userTicketsModel = repository.findByUserIdAndTicketId(userId, ticketId);
			if(userTicketsModel.isEmpty()) return ResponseEntity.notFound().build();
			UserTicketDTO dto = new UserTicketDTO(userTicketsModel.get().getId(), convertUserModelToUserDto(userTicketsModel.get().getUser()), convertTicketModelToTicketDto(userTicketsModel.get().getTicket()));
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	private UserModel getAllUsers(UsersTicketsModel model) {
		return model.getUser();
	}
	private TicketModel getAllTickets(UsersTicketsModel model){
		return model.getTicket();
	}

	private UserDTO convertUserModelToUserDto(UserModel model) {
		return new UserDTO(
				model.getId(),
				model.getUsername(),
				"",
				model.getEmail(),
				model.getName(),
				model.getLastname(),
				model.getBirthday(),
				model.getRole().getRole(),
				convertCityModelToCityDTO(model.getCity()));
	}
	private TicketDTO convertTicketModelToTicketDto(TicketModel model){
		return new TicketDTO(
				model.getId(),
				model.getName(),
				model.getDescription(),
				model.getAmount(),
				model.getEvent().getId()
			);
	}
	private CityDTO convertCityModelToCityDTO(CityModel model){
		return new CityDTO(model.getName(), model.getState());
	}
}
