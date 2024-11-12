package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventsModel {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @Column(name="initial_date")
    private String initialDate;

    @Column(name="final_date")
    private String finalDate;

    @Column(name="description")
    private String description;

    @Column(name="event_image")
    private String eventImage;

    @Column(name="event_status")
    private EventsStatus eventStatus;

    @Column(name="eventAddress_EventAddress_id", nullable = false)
    private String eventAddress;
}
