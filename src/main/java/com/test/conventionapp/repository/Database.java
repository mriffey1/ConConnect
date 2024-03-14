package com.test.conventionapp.repository;

import com.test.conventionapp.model.User;
public interface Database {
    void saveUser(User user);
}