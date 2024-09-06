package com.test.conconnect.service.user;

import com.test.conconnect.model.user.User;

import java.util.concurrent.ExecutionException;

public interface UserService {
    User createUser(String username, String password, String email) throws ExecutionException, InterruptedException;
}