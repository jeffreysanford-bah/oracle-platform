package com.performance.java_api;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DatabaseServiceTest {

    @Mock
    private Connection conn;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @InjectMocks
    private DatabaseService databaseService;

    @Test
    void testFetchUsers() throws SQLException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        when(conn.prepareStatement("SELECT * FROM users")).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getString("id")).thenReturn("1");
        when(rs.getString("name")).thenReturn("John Doe");
    }

    @Test
    void testGetUserById() throws SQLException {
        // Arrange
        MockitoAnnotations.openMocks(this);
        when(conn.prepareStatement("SELECT * FROM users WHERE id = ?")).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getString("id")).thenReturn("1");
        when(rs.getString("name")).thenReturn("John Doe");
    }
}