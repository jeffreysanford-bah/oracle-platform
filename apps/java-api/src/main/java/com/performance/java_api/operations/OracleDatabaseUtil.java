package com.performance.java_api.operations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class OracleDatabaseUtil {

    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USER = "your_db_user";
    private static final String PASSWORD = "your_db_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public static boolean isDatabaseEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM users";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }
        }
        return true;
    }

    public static void insertUsers(List<org.apache.catalina.User> users) throws SQLException {
        String query = "INSERT INTO users (id, name, email, age) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // for (User user : users) {
            //     statement.setLong(1, user.getId());
            //     statement.setString(2, user.getName());
            //     statement.setString(3, user.getEmail());
            //     statement.setInt(4, user.getAge());
            //     statement.addBatch();
            // }
            statement.executeBatch();
        }
    }
}
