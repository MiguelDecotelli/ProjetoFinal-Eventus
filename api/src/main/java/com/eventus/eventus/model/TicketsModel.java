package com.eventus.eventus.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Tickets")
public class TicketsModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="ticket_id")
  private int ticketId;
  @Column(name="ticket_name", nullable = false)
  private String ticketName;
  @Column(name="ticket_value", nullable = false)
  private float ticketValue;
  @Column(name="ticket_description")
  private String ticketDescription;
  @Column(name="ticket_amount", nullable = false)
  private int ticketAmount;
}