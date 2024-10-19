package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name="Events")
public class EventsModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="event_id")
  private int eventId;
  @Column(name="event_name", nullable = false)
  private String eventName;
  @Column(name="event_initial_date", nullable = false)
  private Date eventInitialDate;
  @Column(name="event_final_date", nullable = false)
  private Date eventFinalDate;
  @Column(name="event_description", nullable = false)
  private String eventDescription;
  @Column(name="event_description", nullable = false)
  // TODO: Mudar Tipo para Blob?
  private String eventImage;
  @Column(name="event_status", nullable = false)
  private EventStatusEnum eventStatus;
  // Event Address
  public enum EventStatusEnum{
    ACTIVE,
    CONCLUDED,
    PENDING,
    POSTPONED
  }
}
