package com.test.conconnect.repository;

import com.test.conconnect.model.Event;
import com.test.conconnect.model.User;

import java.util.List;

public interface Database {
    // Methods for event CRUD operations
    List<Event> getEvents();
    Event getEventById(String eventId);
    Event createEvent(Event event);
    Event updateEvent(String eventId, Event updatedEvent);
    boolean deleteEvent(String eventId);

    // Methods for user functionality
    void saveUser(User user);
}
