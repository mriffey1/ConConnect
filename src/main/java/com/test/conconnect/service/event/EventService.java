package com.test.conconnect.service.event;

import com.test.conconnect.model.event.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(String eventId);
    Event createEvent(Event event);
    Event updateEvent(String eventId, Event event);
    boolean deleteEvent(String eventId);
}
