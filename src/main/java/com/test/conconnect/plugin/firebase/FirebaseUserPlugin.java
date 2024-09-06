package com.test.conconnect.plugin.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.*;
import com.test.conconnect.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class FirebaseUserPlugin {
    private final Logger logger = LoggerFactory.getLogger(FirebaseUserPlugin.class);
    private DatabaseReference usersReference;

    // Inject FirebaseInitializer to ensure Firebase is initialized
    public FirebaseUserPlugin(FirebaseInitializer firebaseInitializer) {
        usersReference = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void saveUser(User user) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPasswordHash())
                    .setDisplayName(user.getUsername());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            logger.info("Successfully created user: " + userRecord.getUid());

            String userId = userRecord.getUid();
            Map<String, Object> userData = new HashMap<>();
            userData.put("username", user.getUsername());
            userData.put("email", user.getEmail());

            usersReference.child(userId).setValueAsync(userData);

        } catch (Exception e) {
            logger.error("Error creating user in Firebase Authentication", e);
        }
    }

    public boolean isUsernameExists(String username, String email) throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean exists = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null && (user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(email))) {
                        exists = true;
                        break;
                    }
                }
                future.complete(exists);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future.get();
    }
}
