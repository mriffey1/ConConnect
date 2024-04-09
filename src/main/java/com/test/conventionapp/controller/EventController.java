package com.test.conventionapp.controller;

import com.test.conventionapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String showEvents() {
        return "events";
    }

}
