package config;

import java.io.IOException;
import java.nio.file.*;
import java.sql.*;

public class DatabaseInitializer {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final DatabaseProperties databaseProperties = new DatabaseProperties();

    public void initializeDatabase() {
        processSqlFile(databaseProperties.getCreateTableFilePath());
    }

    public void insertIntoDatabase() {
        processSqlFile(databaseProperties.insertTableFilePath());
    }

    private void processSqlFile(String filename) {
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement())
        {
            executeSqlFile(statement, filename);
        } catch (SQLException e) {
            System.err.println("Error executing SQL from file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading SQL file: " + e.getMessage());
        }
    }

    private void executeSqlFile(Statement statement, String filename) throws IOException, SQLException {
        executeParsedQueries(getSqlFile(filename), statement);
    }

    private String getSqlFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    private void executeParsedQueries(String sql, Statement statement) throws SQLException {
        String[] queries = splitSqlIntoQueries(sql);
        executeSqlStatements(queries, statement);
    }

    private String[] splitSqlIntoQueries(String sql) {
        return sql.split(";");
    }

    private void executeSqlStatements(String[] queries, Statement statement) throws SQLException {
        for (String query : queries) {
            if (!isQueryEmpty(query)) {
                statement.executeUpdate(query.trim() + ";");
                System.out.println(query +" was executed successfully.");
            }
        }
    }

    private boolean isQueryEmpty(String query) {
        return query.trim().isEmpty();
    }
}
