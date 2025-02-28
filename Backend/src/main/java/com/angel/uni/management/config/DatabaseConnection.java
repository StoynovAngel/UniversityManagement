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

    public static Connection getConnection() throws DatabaseConnectionException {
        if (conn == null) {
            try {
                DatabaseProperties databaseProperties = DatabaseProperties.getInstance();
                conn = DriverManager.getConnection(databaseProperties.getDatabaseUrl(), databaseProperties.getProperties());
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                String errorMessage = "Database access error or connection might be null.";
                QueryLogger.logError(errorMessage, e);
            }
        }
        return conn;
    }
}
