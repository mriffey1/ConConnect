package com.test.conventionapp.controller;

import com.test.conventionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/createUser")
    public String showCreateUserForm() {
        return "create-user";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) throws ExecutionException, InterruptedException {
        userService.createUser(username, password, email);
        return "redirect:/login"; // Redirect to login page after user creation
    }
}