package com.test.conconnect.repository.user;

import com.google.firebase.database.*;
import com.test.conconnect.model.user.User;
import com.test.conconnect.repository.Database;
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
        String hashedPassword = passwordEncoder.encode(password);
        user.setPasswordHash(hashedPassword);
        database.saveUser(user);
    }

    @Override
    public boolean isUsernameExists(String username, String email) throws ExecutionException, InterruptedException {
        return database.isUsernameExists(username, email);
    }

}
