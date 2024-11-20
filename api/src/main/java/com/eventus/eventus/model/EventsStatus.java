package com.eventus.eventus.model;

public enum EventsStatus {
    ACTIVE("active"),
    CONCLUDED("concluded"),
    PENDING("pending"),
    POSTPONED("postponed");
    String status;

    EventsStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
