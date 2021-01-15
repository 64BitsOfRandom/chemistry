package com.example.Chemistry.connector;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnector {
    private static Connection connection;
    private static final String dbURL = "jdbc:hsqldb:file:C:/Users/Сергей/IdeaProjects/Chemistry/src/main/resources/database/ChemicalDB.db";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection != null) return connection;
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        connection = DriverManager.getConnection(dbURL);
        return connection;
    }

}
