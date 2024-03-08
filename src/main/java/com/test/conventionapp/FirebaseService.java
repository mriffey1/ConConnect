package com.test.conventionapp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Service class for interacting with Firebase Realtime Database.
 */
@Service
public class FirebaseService {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseService.class);
    /**
     * Initializes FirebaseApp with the provided credentials and database URL.
     */
    @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream(
                    "C:\\Users\\akira\\Documents\\New folder (4)\\ConventionWebApp\\db.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sqlfirebase-32a37-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            logger.error("Error occurred", e);
        }
    }

    /**
     * Retrieves event data along with animals asynchronously from the Firebase Realtime Database.
     *
     * @return A CompletableFuture containing a list of maps representing event data with animals.
     */
    public CompletableFuture<List<Map<String, Object>>> getEventsDataWithAnimals() {
        CompletableFuture<List<Map<String, Object>>> future = new CompletableFuture<>();

        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("Events");

        // Fetch Events data
        eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot eventsSnapshot) {

                List<Map<String, Object>> eventsData = new ArrayList<>();
                // Iterate through Events
                for (DataSnapshot eventSnapshot : eventsSnapshot.getChildren()) {
                    String eventId = eventSnapshot.getKey();
                    Map<String, Object> eventData = eventSnapshot
                            .getValue(new GenericTypeIndicator<>() {
                            });
                    eventData.put("eventId", eventId);
                    eventsData.add(eventData);
                }
                future.complete(eventsData);

            }

            @Override
            public void onCancelled(DatabaseError eventsError) {
                System.out.println("The read failed for Events: " + eventsError.getCode());
                future.completeExceptionally(eventsError.toException());
            }
        });
        return future;
    }
}
