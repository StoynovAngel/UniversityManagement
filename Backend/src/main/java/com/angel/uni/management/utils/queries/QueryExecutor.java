package com.angel.uni.management.utils.queries;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.exceptions.DataAccessException;
import com.angel.uni.management.utils.exceptions.DatabaseConnectionException;

import java.sql.*;
import java.util.*;

/**
 * Provides functionality for executing SELECT, UPDATE, DELETE, and INSERT SQL statements.
 * <p>
 * This class extends {@link BaseQuery} and includes method:
 * </p>
 * <ul>
 *     <li>Parameterized SELECT queries that return a single result or a list of results</li>
 * </ul>
 */

public class QueryExecutor extends BaseQuery {

    public <T> T executeSelect(String sql, CustomRowMapper<?, T> mapper, Object... params) {
        List<T> results = executeQueryList(sql, mapper, params);
        return results.isEmpty() ? null : results.get(0);
    }

    public <T> List<T> executeQueryList(String sql, CustomRowMapper<?, T> mapper, Object... params) {
        if (sql == null || sql.trim().isEmpty()) {
            throw new IllegalArgumentException("SQL query cannot be null or empty.");
        }
        if (mapper == null) {
            throw new IllegalArgumentException("Row mapper cannot be null.");
        }
        QueryValidator.inputValidator(params);

        List<T> results = new ArrayList<>();
        QueryLogger.logDebug("Executing query: " + sql + " | Params: " + Arrays.toString(params));

        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            setParameters(preparedStatement, params);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    try {
                        results.add(mapper.mapRow(resultSet));
                    } catch (Exception e) {
                        QueryLogger.logError("Failed to map row for query: " + sql, e);
                    }
                }
            }
        } catch (IllegalArgumentException | SQLException e) {
            String errorMessage = "Failed to execute query: " + sql + " | Params: " + Arrays.toString(params);
            QueryLogger.logError(errorMessage, e);
            return new ArrayList<>();
        } catch (DatabaseConnectionException e) {
            QueryLogger.logError("Database connection was not established.");
            return new ArrayList<>();
        }
        return results;
    }
}
