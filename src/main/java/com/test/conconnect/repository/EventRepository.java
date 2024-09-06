package com.test.conconnect.repository;

import com.test.conconnect.model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> getAllEvents();
    Event getEventById(String eventId);
    Event createEvent(Event event);
    Event updateEvent(String eventId, Event event);
    boolean deleteEvent(String eventId);
}
