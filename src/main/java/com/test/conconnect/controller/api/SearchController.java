package com.test.conconnect.controller.api;

import com.test.conconnect.service.api.GenericSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@Tag(name = "Search API", description = "Search or Filter by Entity")
public class SearchController {

    private final GenericSearchService searchService;

    @Autowired
    public SearchController(GenericSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    @Operation(summary = "Generic search", description = "Search for any entity based on field and criteria")
    public ResponseEntity<List<Object>> search(
            @RequestParam("entity") String entity,
            @RequestParam("field") String field,
            @RequestParam("contains") String contains) {

        List<Object> results = searchService.search(entity, field, contains);
        System.out.println(results);
        return ResponseEntity.ok(results);
    }
}
