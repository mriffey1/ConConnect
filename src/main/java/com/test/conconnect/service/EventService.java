package com.test.conconnect.service;

import com.test.conconnect.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(String eventId);
    Event createEvent(Event event);
    Event updateEvent(String eventId, Event event);
    boolean deleteEvent(String eventId);
}
