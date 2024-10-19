package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Roles")
public class RoleModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="role_id")
  private int roleId;
  @Column(name="role_name")
  private String roleName;
}
