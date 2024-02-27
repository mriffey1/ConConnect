package com.test.conventionapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;
import com.google.firebase.auth.;;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        try {
            // Create a new user in Firebase Authentication
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword());
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            return "login"; // Redirect to login page after successful registration
        } catch (Exception e) {
            // Handle registration failure
            e.printStackTrace();
            return "redirect:/register?error";
        }
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(User user, HttpSession session) {
        System.out.println(user);
        try {
            // Authenticate user with Firebase Authentication
          
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(user.getEmail());
            System.out.println(userRecord.getEmail());
            
            // firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
            //         .addOnSuccessListener(authResult -> {
            //             // Authentication succeeded
            //             // Get the authenticated user's email
            //             String userEmail = authResult.getUser().getEmail();
            //             // Store user's email in session (or any other user information you may need)
            //             session.setAttribute("userEmail", userEmail);
            //             // Redirect to personalized greeting page
            //             return "redirect:/greeting";
            //         })
            //         .addOnFailureListener(e -> {
            //             // Authentication failed
            //             e.printStackTrace();
            //             return "redirect:/login?error";
            //         });
            return "index";
        } catch (Exception e) {
            // Handle login failure
            e.printStackTrace();
            return "redirect:/login?error";
        }
    }
}
