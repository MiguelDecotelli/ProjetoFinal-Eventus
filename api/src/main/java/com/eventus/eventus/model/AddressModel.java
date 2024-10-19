package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="EventAddress")
public class AddressModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="address_id")
  private int addressId;
  @Column(name="address_cep")
  private int addressCep;
  @Column(name="address_number")
  private int addressNumber;
  @Column(name="address_complement")
  private int addressComplement;
  @Column(name="address_street")
  private int addressStreet;
}