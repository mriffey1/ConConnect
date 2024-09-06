package com.test.conconnect.repository;

import com.test.conconnect.model.event.Event;
import com.test.conconnect.model.user.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Database {

    List<Event> getEvents();
    Event getEventById(String eventId);
    Event createEvent(Event event);
    Event updateEvent(String eventId, Event updatedEvent);
    boolean deleteEvent(String eventId);

    void saveUser(User user);
    boolean isUsernameExists(String username, String email) throws ExecutionException, InterruptedException;

    List<Object> searchEvents(String field, String contains);

}
