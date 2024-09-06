package com.test.conconnect.plugin.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.conconnect.model.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class FirebaseEventPlugin {
    private final Logger logger = LoggerFactory.getLogger(FirebaseEventPlugin.class);
    private DatabaseReference eventsReference;

    // Inject FirebaseInitializer to ensure Firebase is initialized
    public FirebaseEventPlugin(FirebaseInitializer firebaseInitializer) {
        eventsReference = FirebaseDatabase.getInstance().getReference("Events");
    }

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

    public Event getEventById(String eventId) {
        final Event[] event = {null};
        CountDownLatch latch = new CountDownLatch(1);

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

    public Event createEvent(Event event) {
        String eventId = eventsReference.push().getKey();
        event.setEventId(eventId);
        eventsReference.child(eventId).setValueAsync(event);
        return event;
    }

    public Event updateEvent(String eventId, Event updatedEvent) {
        final Event[] existingEvent = {null};
        CountDownLatch latch = new CountDownLatch(1);

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
                    if (event != null) {
                        try {
                            // Use reflection to get the field value
                            String fieldValue = (String) Event.class.getDeclaredMethod("get" + capitalize(field)).invoke(event);

                            // Check if the field value contains the search term
                            if (fieldValue != null && fieldValue.toLowerCase().contains(contains.toLowerCase())) {
                                results.add(event);
                            }
                        } catch (Exception e) {
                            logger.error("Error retrieving field value for search", e);
                        }
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

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
