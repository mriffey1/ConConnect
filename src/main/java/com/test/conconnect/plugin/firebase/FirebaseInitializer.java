package com.test.conconnect.plugin.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirebaseInitializer {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    public FirebaseInitializer() {
        try {
            // Initialize Firebase only once
            if (FirebaseApp.getApps().isEmpty()) {
                FileInputStream serviceAccount = new FileInputStream("C:\\Users\\akira\\Documents\\New folder (4)\\ConConnect\\db.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://sqlfirebase-32a37-default-rtdb.firebaseio.com/")
                        .build();

                FirebaseApp.initializeApp(options);
                logger.info("Firebase initialized successfully.");
            }
        } catch (IOException e) {
            logger.error("Error initializing Firebase", e);
        }
    }
}
