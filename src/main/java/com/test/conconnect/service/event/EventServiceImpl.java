package com.test.conconnect.service.event;

import com.test.conconnect.model.event.Event;
import com.test.conconnect.repository.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @Override
    public Event getEventById(String eventId) {
        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.createEvent(event);
    }

    @Override
    public Event updateEvent(String eventId, Event event) {
        return eventRepository.updateEvent(eventId, event);
    }

    @Override
    public boolean deleteEvent(String eventId) {
        return eventRepository.deleteEvent(eventId);
    }
}
