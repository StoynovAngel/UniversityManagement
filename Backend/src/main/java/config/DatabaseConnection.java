package config;

import java.sql.*;

public class DatabaseConnection {
    private Connection conn;
    public DatabaseConnection() {
        try {
            DatabaseProperties databaseProperties = new DatabaseProperties();
            conn = DriverManager.getConnection(databaseProperties.getDatabaseUrl(), databaseProperties.getProperties());
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
