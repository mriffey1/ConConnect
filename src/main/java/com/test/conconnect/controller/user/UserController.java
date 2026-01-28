package com.test.conconnect.controller.user;

import com.test.conconnect.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/createUser")
    public String showCreateUserForm() {
        return "create-user";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) throws ExecutionException, InterruptedException {
        userService.createUser(username, password, email);

        return "redirect:/login"; // Redirect to login page after user creation
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // renders login.html (Thymeleaf/JSP view)
    }

    // 🔥 LOGIN PROCESSING
    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        var user = userService.findByEmail(email);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        // Save logged-in user in session
        session.setAttribute("loggedInUser", user.getEmail());

        return "redirect:/dashboard";
    }

    // 🔥 DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        String userEmail = (String) session.getAttribute("loggedInUser");

        if (userEmail == null) {
            return "redirect:/login";
        }

        model.addAttribute("userEmail", userEmail);
        return "dashboard";
    }

}