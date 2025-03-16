package com.angel.uni.management.utils.queries;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.QueryResult;
import com.angel.uni.management.utils.exceptions.DataMappingException;
import com.angel.uni.management.utils.exceptions.DatabasePropertiesException;
import com.angel.uni.management.utils.exceptions.QueryExecutionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    protected <T> QueryResult<T> executeSelectQuery(String sql, CustomRowMapper<?, T> mapper, Object... params) {
        try {
            T result = executeSelect(sql, mapper, params);
            return new QueryResult<>(result, null);
        } catch (QueryExecutionException e) {
            QueryLogger.logError("Failed to execute query: " + sql, e);
            return new QueryResult<>(null, "Failed to execute query: " + e.getMessage());
        } catch (DatabasePropertiesException e) {
            QueryLogger.logError("Database connection error", e);
            return new QueryResult<>(null, "Database connection error: " + e.getMessage());
        }
    }

    private <T> T executeSelect(String sql, CustomRowMapper<?, T> mapper, Object... params) throws QueryExecutionException, DatabasePropertiesException {
        List<T> results = executeQueryList(sql, mapper, params);
        return results.isEmpty() ? null : results.get(0);
    }

    protected <T> List<T> executeQueryList(String sql, CustomRowMapper<?, T> mapper, Object... params) throws QueryExecutionException {
        validateInputs(sql, mapper, params);
        List<T> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = getPreparedStatement(sql); ResultSet resultSet = executeQuery(preparedStatement, params)) {
            while (resultSet.next()) {
                mapRowToResult(results, mapper, resultSet, sql, params);
            }
        } catch (SQLException e) {
            handleQueryExecutionError(sql, params, e);
        }

        return results;
    }


    private void validateInputs(String sql, CustomRowMapper<?, ?> mapper, Object[] params) {
        if (sql == null || sql.trim().isEmpty()) {
            throw new IllegalArgumentException("SQL query cannot be null or empty.");
        }
        if (mapper == null) {
            throw new IllegalArgumentException("Row mapper cannot be null.");
        }
        QueryValidator.inputValidator(params);
    }

    private ResultSet executeQuery(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        setParameters(preparedStatement, params);
        return preparedStatement.executeQuery();
    }

    private <T> void mapRowToResult(List<T> results, CustomRowMapper<?, T> mapper, ResultSet resultSet, String sql, Object[] params) throws QueryExecutionException {
        try {
            results.add(mapper.mapRow(resultSet));
        } catch (DataMappingException e) {
            handleDataMappingError(sql, params, e);
        }
    }

    private void handleDataMappingError(String sql, Object[] params, Exception e) throws QueryExecutionException {
        String errorMessage = String.format(
                "Failed to map row for query: %s\n | Params: %s | Error: %s",
                sql, Arrays.toString(params), e.getMessage()
        );
        QueryLogger.logError(errorMessage, e);
        throw new QueryExecutionException(errorMessage, e);
    }

    private void handleQueryExecutionError(String sql, Object[] params, Exception e) throws QueryExecutionException {
        String errorMessage = String.format(
                "Failed to execute query: %s\n | Params: %s | Error: %s",
                sql, Arrays.toString(params), e.getMessage()
        );
        QueryLogger.logError(errorMessage, e);
        throw new QueryExecutionException(errorMessage, e);
    }
}
