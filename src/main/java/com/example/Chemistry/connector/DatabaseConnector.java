package com.example.Chemistry.connector;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnector {
    private static Connection connection;
//    private static final String dbURL = "jdbc:hsqldb:file:C:/Users/Сергей/IdeaProjects/Chemistry/src/main/resources/database/ChemicalDB.db";

    public static Connection getConnection() throws SQLException {

        String jdbcUrlPattern = "jdbc:hsqldb:file:%s";
        String databasePath = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "database", "ChemicalDB.db").toString();
        String databaseUrl = String.format(jdbcUrlPattern, databasePath);


        if(connection != null) return connection;
        connection = DriverManager.getConnection(databaseUrl);

        System.err.println(databaseUrl);
        System.err.println(connection.getMetaData().getMaxColumnsInIndex());

        return connection;
    }

}