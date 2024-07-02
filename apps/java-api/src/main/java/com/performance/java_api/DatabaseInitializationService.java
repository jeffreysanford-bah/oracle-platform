package com.performance.java_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.performance.java_api.operations.OracleDatabaseUtil;
import com.performance.java_api.operations.UserService;

import java.sql.SQLException;
import java.util.List;

@Service
public class DatabaseInitializationService {

    @Autowired
    private UserService userService;

    public void initializeDatabase() {
        try {
            if (OracleDatabaseUtil.isDatabaseEmpty()) {
                List<org.apache.catalina.User> users = userService.fetchUsersFromNestJS();
                OracleDatabaseUtil.insertUsers(users);
                System.out.println("Inserted 1000 users into the database.");
            } else {
                System.out.println("Database is not empty. No need to fetch users.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
