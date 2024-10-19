package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="UsersTickets")
public class UserTicketsModel {
  @Id
  @Column(name="user_tickets_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userTicketsId;
  @Column(name="user_tickets_ticket_status")
  private TicketStatus ticketStatus;
  @Column(name="api_response")
  // TODO: Mudar tipo para blob?
  private String apiResponse;
  @Column(name="user_tickets_user_id")
  // ONE TO MANY
  private int userId;
  @Column(name="user_tickets_ticket_id")
  private int ticketId;
  // ONE TO MANY
  public enum TicketStatus{
    ACTIVE,
    CANCELLED,
    PENDING,
    EXPIRED
  }
}
