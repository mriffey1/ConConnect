package com.test.conconnect.plugin;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
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
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class FirebasePlugin implements Database {
    private final AtomicInteger userIdCounter = new AtomicInteger(1);
    private final Logger logger = LoggerFactory.getLogger(FirebasePlugin.class);
    private final BCryptPasswordEncoder passwordEncoder;

    private DatabaseReference databaseReference;

    public FirebasePlugin(BCryptPasswordEncoder passwordEncoder) {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\akira\\Documents\\New folder (4)\\ConConnect\\db.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sqlfirebase-32a37-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            databaseReference = FirebaseDatabase.getInstance().getReference("Users");

            // Retrieve the last used user ID from the database and initialize userIdCounter accordingly
            databaseReference.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
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
        databaseReference.child(userId).setValueAsync(userData);
    }

    private String generateUserId() {
        // Generate a 5-digit auto-incrementing number
        return String.format("%06d", userIdCounter.getAndIncrement());
    }

    public List<Map<String, Object>> getEvents() {
        List<Map<String, Object>> eventsList = new ArrayList<>();
        DatabaseReference eventsRef = FirebaseDatabase.getInstance().getReference("Events");

        eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> event = (Map<String, Object>) snapshot.getValue();
                    eventsList.add(event);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("Error occurred while retrieving events from database", databaseError.toException());
            }
        });
        return eventsList;  // Return events after retrieval
    }
}