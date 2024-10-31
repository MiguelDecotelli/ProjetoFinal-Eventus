package com.eventus.eventus.dto;

public record AuthenticationDTO (String username, String password){
  public String getUsername() {
    return this.username;
  }
  public String getPassword() {
    return this.password;
  }
}
