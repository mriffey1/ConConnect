package com.test.conconnect.plugin;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.test.conconnect.model.Event;
import com.test.conconnect.model.User;
import com.test.conconnect.repository.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class FirebasePlugin implements Database {
    private final Logger logger = LoggerFactory.getLogger(FirebasePlugin.class);
    private final BCryptPasswordEncoder passwordEncoder;
    private final AtomicInteger userIdCounter = new AtomicInteger(1);

    private DatabaseReference usersReference;
    private DatabaseReference eventsReference;

    public FirebasePlugin(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        try {
            // Initialize Firebase with credentials and database URL
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\akira\\Documents\\New folder (4)\\ConConnect\\db.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sqlfirebase-32a37-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            usersReference = FirebaseDatabase.getInstance().getReference("Users");
            eventsReference = FirebaseDatabase.getInstance().getReference("Events");

            // Initialize user ID counter from Firebase
            usersReference.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String lastUserId = snapshot.getKey();
                        userIdCounter.set(Integer.parseInt(lastUserId) + 1);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    logger.error("Error retrieving last user ID", databaseError.toException());
                }
            });

        } catch (IOException e) {
            logger.error("Error initializing Firebase", e);
        }
    }

    // User functionality
    @Override
    public void saveUser(User user) {
        String userId = generateUserId();
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());

        Map<String, Object> userData = new HashMap<>();
        userData.put("username", user.getUsername());
        userData.put("password", hashedPassword);
        userData.put("email", user.getEmail());

        usersReference.child(userId).setValueAsync(userData);
    }

    private String generateUserId() {
        return String.format("%06d", userIdCounter.getAndIncrement());
    }

    @Override
    public List<Event> getEvents() {
        List<Event> eventsList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    eventsList.add(event);
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("Error retrieving events", databaseError.toException());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Error waiting for events data", e);
        }

        return eventsList;
    }

    @Override
    public Event getEventById(String eventId) {
        CountDownLatch latch = new CountDownLatch(1);
        final Event[] event = {null};

        eventsReference.child(eventId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                event[0] = dataSnapshot.getValue(Event.class);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("Error retrieving event by ID", databaseError.toException());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Error waiting for event data", e);
        }

        return event[0];
    }

    @Override
    public Event createEvent(Event event) {
        String eventId = eventsReference.push().getKey();
        event.setEventId(eventId);
        eventsReference.child(eventId).setValueAsync(event);
        return event;
    }

    @Override
    public Event updateEvent(String eventId, Event updatedEvent) {
        CountDownLatch latch = new CountDownLatch(1);
        final Event[] existingEvent = {null};

        eventsReference.child(eventId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                existingEvent[0] = dataSnapshot.getValue(Event.class);
                if (existingEvent[0] != null) {
                    eventsReference.child(eventId).setValueAsync(updatedEvent);
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("Error updating event", databaseError.toException());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Error waiting for event update", e);
        }

        return existingEvent[0] != null ? updatedEvent : null;
    }

    @Override
    public boolean deleteEvent(String eventId) {
        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] isDeleted = {false};

        eventsReference.child(eventId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    eventsReference.child(eventId).removeValueAsync();
                    isDeleted[0] = true;
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("Error deleting event", databaseError.toException());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Error waiting for event deletion", e);
        }

        return isDeleted[0];
    }

    public List<Object> searchEvents(String field, String contains) {
        List<Object> results = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    if (event != null && field.equalsIgnoreCase("shortDescription")
                            && event.getShortDescription().toLowerCase().contains(contains.toLowerCase())) {
                        results.add(event);
                    }
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("Error during search", databaseError.toException());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Error waiting for search results", e);
        }

        return results;
    }
}
