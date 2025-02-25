package com.angel.uni.management.config;

import com.angel.uni.management.utils.exceptions.DatabaseConnectionException;
import java.sql.*;

/**
 *  DatabaseConnection used to establish connection to the database.
 *  This class uses private constructor to prevent initialization.
 */

public class DatabaseConnection {
    private static Connection conn;

    private DatabaseConnection() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DatabaseProperties databaseProperties = DatabaseProperties.getInstance();
                conn = DriverManager.getConnection(databaseProperties.getDatabaseUrl(), databaseProperties.getProperties());
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                QueryLogger.logError("Database connection failed", e);
                throw new DatabaseConnectionException("Failed to establish database connection", e);
            }
        }
        return conn;
    }
}
