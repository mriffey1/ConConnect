package com.test.conventionapp.repository;

import com.test.conventionapp.model.User;

import java.util.concurrent.ExecutionException;

public interface UserRepository {
    void save(User user, String password);
    boolean isUsernameExists(String username) throws ExecutionException, InterruptedException;
}