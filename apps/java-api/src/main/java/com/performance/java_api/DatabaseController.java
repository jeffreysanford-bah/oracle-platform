package com.performance.java_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DatabaseController {

    @Autowired
    private DatabaseInitializationService databaseInitializationService;

    @GetMapping("/initialize-database")
    public String initializeDatabase() {
        databaseInitializationService.initializeDatabase();
        return "Database initialization complete.";
    }
}
