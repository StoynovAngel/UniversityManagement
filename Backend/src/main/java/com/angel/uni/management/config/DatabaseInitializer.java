package com.angel.uni.management.config;

import com.angel.uni.management.utils.exceptions.DatabaseConnectionException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DatabaseInitializer contains static methods used to create and insert rows into tables.
 * This class uses private constructor to prevent initialization.
 */

public class DatabaseInitializer {
    private static final DatabaseProperties databaseProperties = DatabaseProperties.getInstance();

    private DatabaseInitializer() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static void initializeDatabase() {
        processSqlFile(databaseProperties.getCreateTableFilePath());
    }

    public static void insertIntoDatabase() {
        processSqlFile(databaseProperties.insertTableFilePath());
    }

    private static void processSqlFile(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        try (Connection connection = DatabaseConnection.getConnection()) {
            executeSqlFile(getSqlFile(filename), connection);
        } catch (SQLException e) {
            String errorMessage = "Error executing SQL from file: " + filename;
            QueryLogger.logError(errorMessage, e);
        } catch (DatabaseConnectionException e) {
            QueryLogger.logError("Cannot initialize the database tables nor insert into them.");
        }
    }

    private static void executeSqlFile(String sql, Connection connection) {
        String[] queries = splitSqlIntoQueries(sql);
        executeSqlStatements(queries, connection);
    }

    private static String getSqlFile(String filename) throws DatabaseConnectionException {
        try {
            Path path = Paths.get(filename);
            if (!Files.exists(path)) {
                String errorMessage = "File with this name is not found: " + filename;
                throw new FileNotFoundException(errorMessage);
            }
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            String errorMessage = "Failed to read SQL file: " + filename;
            QueryLogger.logError(errorMessage, e);
            throw new DatabaseConnectionException(errorMessage, e);
        }
    }

    private static String[] splitSqlIntoQueries(String sql) {
        return sql.split(";");
    }

    private static void executeSqlStatements(String[] queries, Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("Connection cannot be null.");
        }
        for (String query : queries) {
            if (!isQueryEmpty(query)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query.trim())) {
                    preparedStatement.executeUpdate();
                    System.out.println("Query executed successfully: " + query.trim());
                } catch (SQLException e) {
                    String errorMessage = "Failed to execute the query.";
                    QueryLogger.logError(errorMessage, e);
                }
            }
        }
    }

    private static boolean isQueryEmpty(String query) {
        return query.trim().isEmpty();
    }
}
