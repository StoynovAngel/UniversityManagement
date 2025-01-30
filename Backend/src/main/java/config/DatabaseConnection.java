package config;

import java.sql.*;

public class DatabaseConnection {
    private static Connection conn;
    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DatabaseProperties databaseProperties = new DatabaseProperties();
                conn = DriverManager.getConnection(databaseProperties.getDatabaseUrl(), databaseProperties.getProperties());
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                System.err.println("Database connection failed: " + e.getMessage());
            }
        }
        return conn;
    }
}
