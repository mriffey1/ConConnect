package com.test.conconnect.repository;

import com.test.conconnect.model.Event;
import com.test.conconnect.model.User;

import java.util.List;

public interface Database {
    void saveUser(User user);
    List<Event> getEvents();
}