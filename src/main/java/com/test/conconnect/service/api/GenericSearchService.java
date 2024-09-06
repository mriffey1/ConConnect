package com.test.conconnect.service.api;

import com.test.conconnect.plugin.firebase.FirebasePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericSearchService {

    private final FirebasePlugin firebasePlugin;

    @Autowired
    public GenericSearchService(FirebasePlugin firebasePlugin) {
        this.firebasePlugin = firebasePlugin;
    }

    public List<Object> search(String entity, String field, String contains) {
        switch (entity.toLowerCase()) {
            case "event":
                return searchEvent(field, contains);
            // Add more cases for other entities if needed
            default:
                throw new IllegalArgumentException("Unsupported entity: " + entity);
        }
    }

    private List<Object> searchEvent(String field, String contains) {
        // Use FirebasePlugin to search events based on the field and contains
        return firebasePlugin.searchEvents(field, contains);
    }
}
