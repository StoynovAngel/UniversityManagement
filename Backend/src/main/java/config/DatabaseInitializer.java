package config;

import java.io.IOException;
import java.nio.file.*;
import java.sql.*;

/**
 *  DatabaseInitializer contains static methods used to create and insert rows into tables.
 *  This class uses private constructor to prevent initialization.
 */

public class DatabaseInitializer {
    private static final DatabaseProperties databaseProperties = DatabaseProperties.getUniqueInstance();

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
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement())
        {
            executeSqlFile(statement, filename);
        } catch (SQLException e) {
            System.err.println("Error executing SQL from file: " + e.getMessage());
        }
    }

    private static void executeSqlFile(Statement statement, String filename) throws SQLException {
        executeParsedQueries(getSqlFile(filename), statement);
    }

    private static String getSqlFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            QueryLogger.logError("Failed to read SQL file: " + filename, e);
            throw new RuntimeException("Database initialization failed", e);
        }
    }

    private static void executeParsedQueries(String sql, Statement statement) throws SQLException {
        String[] queries = splitSqlIntoQueries(sql);
        executeSqlStatements(queries, statement);
    }

    private static String[] splitSqlIntoQueries(String sql) {
        return sql.split(";");
    }

    private static void executeSqlStatements(String[] queries, Statement statement) throws SQLException {
        for (String query : queries) {
            if (!isQueryEmpty(query)) {
                statement.executeUpdate(query.trim() + ";");
                System.out.println(query + " was executed successfully.");
            }
        }
    }

    private static boolean isQueryEmpty(String query) {
        return query.trim().isEmpty();
    }
}
