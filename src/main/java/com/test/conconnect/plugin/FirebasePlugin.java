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
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class FirebasePlugin implements Database {
    private final AtomicInteger userIdCounter = new AtomicInteger(1);
    private final Logger logger = LoggerFactory.getLogger(FirebasePlugin.class);
    private final BCryptPasswordEncoder passwordEncoder;

    private DatabaseReference usersReference;
    private DatabaseReference eventsReference;

    public FirebasePlugin(BCryptPasswordEncoder passwordEncoder) {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\akira\\Documents\\New folder (4)\\ConConnect\\db.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sqlfirebase-32a37-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            usersReference = FirebaseDatabase.getInstance().getReference("Users");
            eventsReference = FirebaseDatabase.getInstance().getReference("Events");

            // Retrieve the last used user ID from the database and initialize userIdCounter accordingly
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
                    logger.error("Error occurred while retrieving last user ID from database", databaseError.toException());
                }
            });

        } catch (IOException e) {
            logger.error("Error occurred while initializing FirebaseApp", e);
        }
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        // Hash the password before saving
        String userId = generateUserId();
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());

        Map<String, Object> userData = new HashMap<>();
        userData.put("username", user.getUsername());
        userData.put("password", hashedPassword); // Save hashed password to database
        userData.put("email", user.getEmail());

        // Save user data to the Realtime Database under "Users" node
        usersReference.child(userId).setValueAsync(userData);
    }

    @Override
    public List<Event> getEvents() {
        List<Event> eventsList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for Firebase response

        eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    eventsList.add(event);
                }
                latch.countDown(); // Release the latch when data is fully loaded
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("Error occurred while retrieving events", databaseError.toException());
                latch.countDown(); // Release the latch even if there's an error
            }
        });

        try {
            latch.await(); // Wait until data is loaded
        } catch (InterruptedException e) {
            logger.error("Error occurred while waiting for event data", e);
        }

        return eventsList; // Now it will return the fully populated list
    }

    private String generateUserId() {
        // Generate a 6-digit auto-incrementing number
        return String.format("%06d", userIdCounter.getAndIncrement());
    }
}
