package com.angel.uni.management.config;

import com.angel.uni.management.utils.exceptions.DatabasePropertiesException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  DatabaseConnection used to establish connection to the database.
 *  This class uses private constructor to prevent initialization.
 */

public final class DatabaseConnection {

    private static Connection conn;

    private DatabaseConnection() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DatabaseProperties databaseProperties = DatabaseProperties.getInstance();
                conn = DriverManager.getConnection(
                        databaseProperties.getDatabaseUrl(),
                        databaseProperties.getProperties()
                );
            } catch (SQLException e) {
                String errorMessage = "Exiting the program. Failed to establish a database connection: " + e.getMessage();
                QueryLogger.logError(errorMessage);
                System.exit(1);
            } catch (DatabasePropertiesException e) {
                String errorMessage = "Exiting the program. Properties were not correct: " + e.getMessage();
                QueryLogger.logError(errorMessage);
                System.exit(1);
            }
        }
        return conn;
    }
}
