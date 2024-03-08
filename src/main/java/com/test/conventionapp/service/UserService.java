package com.test.conventionapp.service;

import com.test.conventionapp.model.User;

import java.util.concurrent.ExecutionException;

public interface UserService {
    User createUser(String username, String password, String email) throws ExecutionException, InterruptedException;
}