package com.eventus.eventus.model;

public enum UserRole{
    BASIC(1),
    ADMIN(2);
    int roleId;
    UserRole(int roleId){
      this.roleId = roleId;
    }
    public int getRoleId(){
      return roleId;
    }
}
