package com.eventus.eventus.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private int userId;
    private String userUsername;
    private String userEmail;
    private String userName;
    private String userLastname;
    private Date userBirthday;
    private String userRole;
}
