package com.test.conventionapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController {

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/")
    public String index(Model model) throws InterruptedException, ExecutionException {
        CompletableFuture<List<Map<String, Object>>> eventsDataFuture = firebaseService
                .geteventsDataWithAnimals();

        // Wait for the CompletableFuture to complete and retrieve the result
        List<Map<String, Object>> eventsData = eventsDataFuture.get();


        

        // Add the data to the model
        model.addAttribute("eventsData", eventsData);

        // Return the view name
        return "index";
    }
}
