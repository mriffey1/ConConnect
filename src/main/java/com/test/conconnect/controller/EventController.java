package com.test.conconnect.controller;

import com.test.conconnect.model.Event;
import com.test.conconnect.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String getEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("eventsData", events);
        return "events";
    }
}
