package com.test.conconnect.controller;

import com.test.conconnect.service.EventService;
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
