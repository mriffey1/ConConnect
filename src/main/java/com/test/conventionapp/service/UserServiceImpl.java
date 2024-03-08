package com.test.conventionapp.service;

import com.test.conventionapp.model.User;
import com.test.conventionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(String username, String password, String email) throws ExecutionException, InterruptedException {
        // Check if username is available
        if (userRepository.isUsernameExists(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Proceed with user creation if username is available
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        // Save user to Firebase Realtime Database
        userRepository.save(user, password, email);

        return user;
    }
}
