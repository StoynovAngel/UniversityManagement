package config;

import java.sql.*;

public class DatabaseConnection {
    private static Connection conn;

    private DatabaseConnection() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DatabaseProperties databaseProperties = DatabaseProperties.getUniqueInstance();
                conn = DriverManager.getConnection(databaseProperties.getDatabaseUrl(), databaseProperties.getProperties());
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                System.err.println("Database connection failed: " + e.getMessage());
            }
        }
        return conn;
    }
}
