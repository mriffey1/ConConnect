package com.test.conconnect.plugin.firebase;

import com.test.conconnect.model.event.Event;
import com.test.conconnect.model.user.User;
import com.test.conconnect.repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class FirebasePlugin implements Database {

    private final FirebaseUserPlugin userPlugin;
    private final FirebaseEventPlugin eventPlugin;

    @Autowired
    public FirebasePlugin(FirebaseUserPlugin userPlugin, FirebaseEventPlugin eventPlugin) {
        this.userPlugin = userPlugin;
        this.eventPlugin = eventPlugin;
    }

    // Delegate User operations
    @Override
    public void saveUser(User user) {
        userPlugin.saveUser(user);
    }

    @Override
    public boolean isUsernameExists(String username, String email) throws ExecutionException, InterruptedException {
        return userPlugin.isUsernameExists(username, email);
    }

    // Delegate Event operations
    @Override
    public List<Event> getEvents() {
        return eventPlugin.getEvents();
    }

    @Override
    public Event getEventById(String eventId) {
        return eventPlugin.getEventById(eventId);
    }

    @Override
    public Event createEvent(Event event) {
        return eventPlugin.createEvent(event);
    }

    @Override
    public Event updateEvent(String eventId, Event updatedEvent) {
        return eventPlugin.updateEvent(eventId, updatedEvent);
    }

    @Override
    public boolean deleteEvent(String eventId) {
        return eventPlugin.deleteEvent(eventId);
    }

    @Override
    public List<Object> searchEvents(String field, String contains) {
        return eventPlugin.searchEvents(field, contains);
    }
}
