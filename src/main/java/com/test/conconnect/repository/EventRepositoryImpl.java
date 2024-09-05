package com.test.conconnect.repository;

import com.test.conconnect.model.Event;
import com.test.conconnect.plugin.FirebasePlugin;
import com.test.conconnect.repository.Database;
import com.test.conconnect.repository.EventRepository;
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
        return database.getEvents(); //
    }
}
