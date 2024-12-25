package config;

import java.io.IOException;
import java.nio.file.*;
import java.sql.*;

public class DatabaseInitializer {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public void initializeDatabase() {
        processSqlFile();
    }

    private void processSqlFile() {
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement())
        {
            executeSqlFile(statement);
        } catch (SQLException e) {
            System.err.println("Error executing SQL from file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading SQL file: " + e.getMessage());
        }
    }

    private void executeSqlFile(Statement statement) throws IOException, SQLException {
        String sql = getSqlFile();
        executeParsedQueries(sql, statement);
    }

    private String getSqlFile() throws IOException {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        return new String(Files.readAllBytes(Paths.get(databaseProperties.getCreateTableFilePath())));
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
