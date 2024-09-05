package com.test.conconnect.repository;

import com.test.conconnect.model.User;

import java.util.concurrent.ExecutionException;

public interface UserRepository {
    void save(User user, String password, String email);
    boolean isUsernameExists(String username, String email) throws ExecutionException, InterruptedException;

}