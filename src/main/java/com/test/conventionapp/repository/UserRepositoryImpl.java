package com.test.conventionapp.repository;

import com.google.firebase.database.*;
import com.test.conventionapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Database database;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryImpl(Database database, BCryptPasswordEncoder passwordEncoder) {
        this.database = database;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user, String password, String email) {
        // Hash and set the password before saving
        String hashedPassword = passwordEncoder.encode(password);
        user.setPasswordHash(hashedPassword);

        database.saveUser(user);
    }

    @Override
    public boolean isUsernameExists(String username) throws ExecutionException, InterruptedException {
         DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(username);
         CompletableFuture<DataSnapshot> future = new CompletableFuture<>();
         userRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 future.complete(dataSnapshot);
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {
                 future.completeExceptionally(databaseError.toException());
             }
         });
         return future.get().exists();
    }
}
