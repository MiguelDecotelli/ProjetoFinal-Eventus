package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name="Users")
public class UserModel {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name="user_username", nullable = false, unique = true)
    private String userUsername;
    @Column(name="user_password", nullable = false, unique = true)
    private String userPassword;
    @Column(name="user_email", nullable = false, unique = true)
    private String userEmail;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_lastname")
    private String userLastname;
    @Column(name="user_birthday")
    private Date userBirthday;
    @Column(name="user_role")
    private String userRole;
}