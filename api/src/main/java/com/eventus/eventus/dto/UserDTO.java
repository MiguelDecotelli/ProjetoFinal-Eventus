package com.eventus.eventus.dto;

import com.eventus.eventus.model.CityModel;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    private Date birthday;
    private String role;
    private CityModel city;
}
