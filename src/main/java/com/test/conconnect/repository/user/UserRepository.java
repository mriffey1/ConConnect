package com.test.conconnect.repository.user;

import com.test.conconnect.model.user.User;

import java.util.concurrent.ExecutionException;

public interface UserRepository {
    void save(User user, String password, String email);
    boolean isUsernameExists(String username, String email) throws ExecutionException, InterruptedException;

}