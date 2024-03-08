package com.test.conventionapp.plugin;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.conventionapp.model.User;
import com.test.conventionapp.repository.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FirebasePlugin implements Database {

    private final Logger logger = LoggerFactory.getLogger(FirebasePlugin.class);
    private final BCryptPasswordEncoder passwordEncoder;

    private DatabaseReference databaseReference;


    public FirebasePlugin(BCryptPasswordEncoder passwordEncoder) {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\akira\\Documents\\New folder (4)\\ConventionWebApp\\db.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sqlfirebase-32a37-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        } catch (IOException e) {
            logger.error("Error occurred while initializing FirebaseApp", e);
        }
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());

        Map<String, Object> userData = new HashMap<>();
        userData.put("username", user.getUsername());
        userData.put("password", hashedPassword); // Save hashed password to database
        userData.put("email", user.getEmail());

        // Save user data to the Realtime Database under "Users" node
        databaseReference.child(user.getUsername()).setValueAsync(userData);
    }
}