package com.angel.uni.management.utils.queries;

import com.angel.uni.management.config.DatabaseConnection;

import java.sql.*;

/**
 * Provides foundational methods for executing SQL queries.
 */

public class BaseQuery {

    protected Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    protected void executeStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement == null) throw new IllegalArgumentException("PreparedStatement is null.");
        preparedStatement.executeUpdate();
    }

    protected PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (query == null || query.isEmpty()) throw new IllegalArgumentException("Query is null or empty.");
        return getConnection().prepareStatement(query);
    }

    protected void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        if (preparedStatement == null) throw new IllegalArgumentException("PreparedStatement is null");
        if (params.length == 0) throw new IllegalArgumentException("Object parameters are 0");
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
