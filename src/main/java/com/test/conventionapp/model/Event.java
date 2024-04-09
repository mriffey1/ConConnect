package com.test.conventionapp.model;

public class Event {
    String ageRequired;
    String email;
    String startDate;
    String endDate;
    Integer eventCost;
    Integer eventDuration;

    public String getAgeRequired() {
        return ageRequired;
    }

    public void setAgeRequired(String ageRequired) {
        this.ageRequired = ageRequired;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getEventCost() {
        return eventCost;
    }

    public void setEventCost(Integer eventCost) {
        this.eventCost = eventCost;
    }

    public Integer getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Integer eventDuration) {
        this.eventDuration = eventDuration;
    }
}
