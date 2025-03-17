package com.angel.uni.management.config;

import com.angel.uni.management.utils.exceptions.DatabasePropertiesException;

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

public final class DatabaseInitializer {
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
        } catch (IllegalArgumentException e) {
            QueryLogger.logError("Error occurred in the filename:" + filename, e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            String errorMessage = "Error executing SQL from file: " + filename;
            QueryLogger.logError(errorMessage, e);
            System.exit(1);
        } catch (IOException e) {
            String errorMessage = "Failed to read SQL file: " + filename;
            QueryLogger.logError(errorMessage, e);
            System.exit(1);
        }
    }

    private static void executeSqlFile(String sql, Connection connection) {
        String[] queries = splitSqlIntoQueries(sql);
        executeSqlStatements(queries, connection);
    }

    private static String getSqlFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            String errorMessage = "File with this name is not found: " + filename;
            throw new FileNotFoundException(errorMessage);
        }
        return new String(Files.readAllBytes(path));
    }

    private static String[] splitSqlIntoQueries(String sql) {
        return sql.split(";");
    }

    private static void executeSqlStatements(String[] queries, final Connection connection) {
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
