package com.example.Chemistry.connector;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.sql.*;

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
        return connection;
    }
    public static void main(String[] args) throws Exception {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM IONS");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String valence = resultSet.getString("valence");
            String notation = resultSet.getString("notation");
            String type = resultSet.getString("type");
            System.out.printf("Valence: %s, notation: %s, type: %s\n",valence,notation,type);
        }
    }
}
