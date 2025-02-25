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
            QueryLogger.logError("Error executing SQL from file: " + filename, e);
            throw new RuntimeException("Database initialization failed", e);
        }
    }

    private static String getSqlFile(String filename) {
        try {
            Path path = Paths.get(filename);
            if (!Files.exists(path)) {
                QueryLogger.logError("File with this name is not found: " + filename);
                throw new FileNotFoundException("File with this name is not found: " + filename);
            }
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            QueryLogger.logError("Failed to read SQL file: " + filename, e);
            throw new RuntimeException("Database initialization failed", e);
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
                    QueryLogger.logError("Failed to execute query: " + query, e);
                    throw new SQLException("Error executing query: " + query, e);
                }
            }
        }
    }

    private static boolean isQueryEmpty(String query) {
        return query.trim().isEmpty();
    }
}
