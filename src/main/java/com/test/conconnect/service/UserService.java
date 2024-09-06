package com.test.conconnect.service;

import com.test.conconnect.model.User;

import java.util.concurrent.ExecutionException;

public interface UserService {
    User createUser(String username, String password, String email) throws ExecutionException, InterruptedException;
}