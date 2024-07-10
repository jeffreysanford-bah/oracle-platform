package com.performance.java_api;

import models.User;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class DatabaseController {

    private final DatabaseService databaseService;
    private boolean isDatabaseAlive = false;
    User user = null;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.isDatabaseAlive = databaseService.isDatabaseAlive();
        System.out.println("Database connection established" + isDatabaseAlive);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        this.databaseService.getUserById(id).subscribe(
            (userById) -> {
                this.user = userById;
            },
            error -> {
                System.out.println("An error occurred: " + error);
            }
        );
        
        return this.user;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return databaseService.fetchUsers().blockingFirst();
    }
}