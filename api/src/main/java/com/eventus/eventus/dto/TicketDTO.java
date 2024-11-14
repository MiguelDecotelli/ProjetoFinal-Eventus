package com.eventus.eventus.dto;

import lombok.Data;

@Data
public class TicketDTO{
  private int id;
  private String name;
  private String description;
  private int amount;
}
