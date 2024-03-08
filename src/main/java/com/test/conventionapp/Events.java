package com.test.conventionapp;


public class Events {
    private String eventId;
    private String eventTitle;
    private String eventType;
    private String gameSystem;
    private String eventCost;
    private String email;
    private String endDate;
    private String eventLocation;
    private String experienceRequired;
    private String ageRequired;
    private String lastUpdated;
    private String longDescription;
    private String shortDescription;
    private String rmName;
    private int ticketAvail;

    /**
     * Get the number of tickets available for event.
     *
     * @return The number of tickets available as int.
     */
    public int getTicketAvail() {
        return ticketAvail;
    }

    /**
     * Set the number of tickets available for event
     *
     * @param ticketAvail The unique identifier for the event.
     */
    public void setTicketAvail(int ticketAvail) {
        this.ticketAvail = ticketAvail;
    }

    /**
     * Get the email address for event organizer
     *
     * @return The email of the event organizer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address for event organizer.
     *
     * @param email The email of the event organizer.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Get the end date of the event.
     *
     * @return The end date of the event.
     */
    public String getEndDate() {
        return endDate;
    }
    /**
     * Set the end date of the event.
     *
     * @param endDate The end date of the event.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * Get the location of the event.
     *
     * @return The location of the event.
     */
    public String getEventLocation() {
        return eventLocation;
    }
    /**
     * Set the location of the event.
     *
     * @param eventLocation The location of the event.
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
    /**
     * Get the experience required for the event.
     *
     * @return The experience required for the event.
     */
    public String getExperienceRequired() {
        return experienceRequired;
    }
    /**
     * Set the experience required for the event.
     *
     * @param experienceRequired The experience required for the event.
     */
    public void setExperienceRequired(String experienceRequired) {
        this.experienceRequired = experienceRequired;
    }
    /**
     * Get the minimum age required for attending the event.
     *
     * @return The minimum age required for attending the event.
     */
    public String getAgeRequired() {
        return ageRequired;
    }
    /**
     * Set the minimum age required for attending the event.
     *
     * @param ageRequired The minimum age required for attending the event.
     */
    public void setAgeRequired(String ageRequired) {
        this.ageRequired = ageRequired;
    }
    /**
     * Get the last updated date of the event information.
     *
     * @return The last updated date of the event information.
     */
    public String getLastUpdated() {
        return lastUpdated;
    }
    /**
     * Set the last updated date of the event information.
     *
     * @param lastUpdated The last updated date of the event information.
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    /**
     * Get the long description of the event.
     *
     * @return The long description of the event.
     */
    public String getLongDescription() {
        return longDescription;
    }
    /**
     * Set the long description of the event.
     *
     * @param longDescription The long description of the event.
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    /**
     * Get the short description of the event.
     *
     * @return The short description of the event.
     */
    public String getShortDescription() {
        return shortDescription;
    }
    /**
     * Set the short description of the event.
     *
     * @param shortDescription The short description of the event.
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    /**
     * Get the room name where the event will be held.
     *
     * @return The room name where the event will be held.
     */
    public String getRmName() {
        return rmName;
    }
    /**
     * Set the room name where the event will be held.
     *
     * @param rmName The room name where the event will be held.
     */
    public void setRmName(String rmName) {
        this.rmName = rmName;
    }
    /**
     * Get unique event ID
     *
     * @return The unique event ID.
     */
    public String getEventId() {
        return eventId;
    }
    /**
     * Set the unique event ID
     *
     * @param eventId The unique event ID.
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    /**
     * Get event Title.
     *
     * @return The event title.
     */
    public String getEventTitle() {
        return eventTitle;
    }



    /**
     * Set the event title.
     *
     * @param eventTitle The title of the event.
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    /**
     * Get event type.
     *
     * @return The event type.
     */
    public String getEventType() {
        return eventType;
    }
    /**
     * Set the event type.
     *
     * @param eventType The event type.
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    /**
     * Get game system for event.
     *
     * @return The game system for event.
     */
    public String getGameSystem() {
        return gameSystem;
    }
    /**
     * Set the game system of the event.
     *
     * @param gameSystem The game system of the event.
     */
    public void setGameSystem(String gameSystem) {
        this.gameSystem = gameSystem;
    }
    /**
     * Get event cost.
     *
     * @return The event cost.
     */
    public String getEventCost() {
        return eventCost;
    }
    /**
     * Set the event cost.
     *
     * @param eventCost The event cost.
     */
    public void setEventCost(String eventCost) {
        this.eventCost = eventCost;
    }





}
