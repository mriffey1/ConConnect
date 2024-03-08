package com.test.conventionapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Controller class for handling requests related to the home page.
 */
@Controller
public class HomeController {

    private final FirebaseService firebaseService;

    public HomeController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    /**
     * Handler method for the home page.
     *
     * @param model The model to which data will be added for rendering the view.
     * @return The name of the view template to render.
     * @throws InterruptedException If the thread is interrupted while waiting.
     * @throws ExecutionException   If an error occurs during the execution of the CompletableFuture.
     */
    @GetMapping("/")
    public String index(Model model) throws InterruptedException, ExecutionException {
        // Retrieve event data asynchronously
        CompletableFuture<List<Map<String, Object>>> eventsDataFuture = firebaseService
                .getEventsDataWithAnimals();

        // Wait for the CompletableFuture to complete and retrieve the result
        List<Map<String, Object>> eventsData = eventsDataFuture.get();

        // Add the event data to the model
        model.addAttribute("eventsData", eventsData);

        // Return the name of the view template to render
        return "index";
    }
}
