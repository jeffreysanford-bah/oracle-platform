package com.performance.java_api;

import io.reactivex.rxjava3.core.Observable;
import models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "sys";
    private static final String DB_PASSWORD = "12345678";
    private Connection conn;

    public boolean DatabaseConnectionInit() {
        try {
            this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }

        System.out.println("Database connection established");    
        return true;
    }

    public boolean isDatabaseAlive() {
        return conn != null;
    }   

    Observable<List<User>> fetchUsers() {
        return Observable.create(emitter -> { 
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users")) {
                ResultSet rs = stmt.executeQuery();
                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    User user = new User(rs.getString("id"), rs.getString("name"));
                    users.add(user);
                }
                emitter.onNext(users);
                emitter.onComplete();
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }

    Observable<User> getUserById(String id) {
        return Observable.create(emitter -> {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    User user = new User(rs.getString("id"), rs.getString("name")); // replace with your User constructor
                    emitter.onNext(user);
                }
                emitter.onComplete();
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }

    // void insertUsersIntoOracle(List<User> users) {
    //     try {
    //         for (User user : users) {
    //             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
    //             stmt.setString(1, user.getId());
    //             stmt.setString(2, user.getFullNameById(user.));
    //             stmt.executeUpdate();
    //         }
    //     } catch (SQLException e) {
    //         throw new RuntimeException("Error inserting users into database", e);
    //     }
    // }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing database connection", e);
            }
        }
    }
}