package com.test.conconnect.service.api;

import com.test.conconnect.repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericSearchService {

    private final Database database;

    @Autowired
    public GenericSearchService(Database database) {
        this.database = database;
    }

    public List<Object> search(String entity, String field, String contains) {
        switch (entity.toLowerCase()) {
            case "event":
                return searchEvent(field, contains);
            default:
                throw new IllegalArgumentException("Unsupported entity: " + entity);
        }
    }

    private List<Object> searchEvent(String field, String contains) {
        return database.searchEvents(field, contains);
    }
}