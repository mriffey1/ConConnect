package com.test.conconnect.repository.event;

import com.test.conconnect.model.event.Event;
import com.test.conconnect.repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final Database database;

    @Autowired
    public EventRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Event> getAllEvents() {
        return database.getEvents();
    }

    @Override
    public Event getEventById(String eventId) {
        return database.getEventById(eventId);
    }

    @Override
    public Event createEvent(Event event) {
        return database.createEvent(event);
    }

    @Override
    public Event updateEvent(String eventId, Event event) {
        return database.updateEvent(eventId, event);
    }

    @Override
    public boolean deleteEvent(String eventId) {
        return database.deleteEvent(eventId);
    }
}
