package com.eventus.eventus.model;

public enum UserRole{
    BASIC("basic"),
    ADMIN("admin");
    String role;
    UserRole(String role){
      this.role = role;
    }
    public String getRole(){
      return this.role;
    }
}
