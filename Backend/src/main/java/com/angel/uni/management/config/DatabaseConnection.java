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
                String errorMessage = "Failed to establish database connection";
                QueryLogger.logError(errorMessage, e);
                throw new DatabaseConnectionException(errorMessage, e);
            }
        }
        return conn;
    }
}
