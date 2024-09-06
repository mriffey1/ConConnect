package com.test.conconnect.model.event;

public class Event {
    private String eventId;
    private String eventTitle;
    private String eventType;
    private String gameSystem;
    private int eventCost;
    private String shortDescription;
    private String eventLocation;
    private String startDate;
    private int ticketAvail;

    // Getters and Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getEventTitle() { return eventTitle; }
    public void setEventTitle(String eventTitle) { this.eventTitle = eventTitle; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getGameSystem() { return gameSystem; }
    public void setGameSystem(String gameSystem) { this.gameSystem = gameSystem; }

    public int getEventCost() { return eventCost; }
    public void setEventCost(int eventCost) { this.eventCost = eventCost; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getEventLocation() { return eventLocation; }
    public void setEventLocation(String eventLocation) { this.eventLocation = eventLocation; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public int getTicketAvail() { return ticketAvail; }
    public void setTicketAvail(int ticketAvail) { this.ticketAvail = ticketAvail; }
}
