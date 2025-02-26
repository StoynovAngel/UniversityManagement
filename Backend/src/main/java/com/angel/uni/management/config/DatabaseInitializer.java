package com.angel.uni.management.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.sql.*;

/**
 *  DatabaseInitializer contains static methods used to create and insert rows into tables.
 *  This class uses private constructor to prevent initialization.
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
        try (Connection connection = DatabaseConnection.getConnection()) {
            executeSqlFile(getSqlFile(filename), connection);
        } catch (SQLException e) {
            String errorMessage = "Error executing SQL from file: " + filename;
            QueryLogger.logError(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private static String getSqlFile(String filename) {
        try {
            Path path = Paths.get(filename);
            if (!Files.exists(path)) {
                String errorMessage = "File with this name is not found"  + filename;
                QueryLogger.logError(errorMessage);
                throw new FileNotFoundException(errorMessage);
            }
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            String errorMessage = "Failed to read SQL file: " + filename;
            QueryLogger.logError(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private static void executeSqlFile(String sql, Connection connection) throws SQLException {
        String[] queries = splitSqlIntoQueries(sql);
        executeSqlStatements(queries, connection);
    }

    private static String[] splitSqlIntoQueries(String sql) {
        return sql.split(";");
    }

    private static void executeSqlStatements(String[] queries, Connection connection) throws SQLException {
        for (String query : queries) {
            if (!isQueryEmpty(query)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query.trim())) {
                    preparedStatement.executeUpdate();
                    System.out.println(query.trim() + " was executed successfully.");
                } catch (SQLException e) {
                    String errorMessage = "Failed to execute query: " + query;
                    QueryLogger.logError(errorMessage, e);
                    throw new SQLException(errorMessage, e);
                }
            }
        }
    }

    private static boolean isQueryEmpty(String query) {
        return query.trim().isEmpty();
    }
}
