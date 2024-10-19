package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Cities")
public class CitiesModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="city_id")
  private int cityId;
  @Column(name="city_name", nullable = false)
  private String cityName;
  @Column(name="city_state", nullable = false)
  private String cityState;
}
