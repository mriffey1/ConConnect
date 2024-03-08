package com.test.conventionapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class for handling user-related requests.
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * Handler method for displaying the registration form.
     *
     * @param model The model to which data will be added for rendering the view.
     * @return The name of the view template to render.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handler method for registering a new user.
     *
     * @param user The user object containing registration information.
     * @return The name of the view template to render after registration.
     */
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
            logger.error("Error occurred during user registration", e);
            return "redirect:/register?error";
        }
    }

    /**
     * Handler method for displaying the login form.
     *
     * @param model The model to which data will be added for rendering the view.
     * @return The name of the view template to render.
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    /**
     * Handler method for authenticating and logging in a user.
     *
     * @param user    The user object containing login credentials.
     * @return The name of the view template to render after login.
     */
    @PostMapping("/login")
    public String loginUser(User user) {
        System.out.println(user);
        try {
            // Authenticate user with Firebase Authentication
//            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(user.getEmail());
            System.out.println(userRecord.getEmail());

            // Handle login success
            return "index";
        } catch (Exception e) {
            // Handle login failure
            logger.error("Error occurred during user login", e);
            return "redirect:/login?error";
        }
    }
}
