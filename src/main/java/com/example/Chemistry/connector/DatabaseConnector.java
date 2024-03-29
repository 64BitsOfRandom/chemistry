package com.example.Chemistry.connector;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnector {
    private static Connection connection;
    private static final String jdbcUrlPattern = "jdbc:hsqldb:file:%s";

    public static Connection getConnection() throws SQLException {
        String databasePath = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "database", "ChemicalDB.db").toString();
        String databaseUrl = String.format(jdbcUrlPattern, databasePath);
        if (connection != null && !connection.isClosed()) return connection;
        connection = DriverManager.getConnection(databaseUrl);
        return connection;
    }
}
