package com.test.conconnect.repository.user;

import com.google.firebase.database.*;
import com.test.conconnect.model.user.User;
import com.test.conconnect.repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
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
        String hashedPassword = passwordEncoder.encode(password);
        user.setPasswordHash(hashedPassword);
        database.saveUser(user);
    }

    @Override
    public boolean isUsernameExists(String username, String email) throws ExecutionException, InterruptedException {
        return database.isUsernameExists(username, email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance()
                .getReference("users"); // <-- make sure this matches where you store users

        CompletableFuture<Optional<User>> future = new CompletableFuture<>();

        Query query = usersRef.orderByChild("email").equalTo(email).limitToFirst(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    future.complete(Optional.empty());
                    return;
                }

                // get the first matching user
                for (DataSnapshot child : snapshot.getChildren()) {
                    User user = child.getValue(User.class);

                    // (optional) if your User has an id field you want to set:
                    // user.setId(child.getKey());

                    future.complete(Optional.ofNullable(user));
                    return;
                }

                future.complete(Optional.empty());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });

        try {
            return future.get(); // blocks until Firebase returns
        } catch (Exception e) {
            // log it if you want
            return Optional.empty();
        }
    }


}
