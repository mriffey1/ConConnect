package com.test.conconnect.controller.event;

import com.test.conconnect.model.event.Event;
import com.test.conconnect.service.event.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Event Management", description = "Operations pertaining to event management in the application")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @Operation(summary = "Get all events", description = "Retrieve a list of all events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID", description = "Retrieve details of a specific event by its ID")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        Event event = eventService.getEventById(id);
        return event != null ? ResponseEntity.ok(event) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a new event", description = "Create a new event in the system")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing event", description = "Update the details of an existing event by ID")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return updatedEvent != null ? ResponseEntity.ok(updatedEvent) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event", description = "Delete an event from the system by its ID")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        boolean isDeleted = eventService.deleteEvent(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
