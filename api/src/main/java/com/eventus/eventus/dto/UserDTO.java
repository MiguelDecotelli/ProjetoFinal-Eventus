package com.eventus.eventus.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private Date birthday;
}
