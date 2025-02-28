package com.angel.uni.management.utils.queries;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.exceptions.DataMappingException;
import com.angel.uni.management.utils.exceptions.DatabaseConnectionException;
import com.angel.uni.management.utils.exceptions.QueryExecutionException;

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

    public <T> T executeSelect(String sql, CustomRowMapper<?, T> mapper, Object... params) throws QueryExecutionException {
        List<T> results = executeQueryList(sql, mapper, params);
        return results.isEmpty() ? null : results.get(0);
    }

    public <T> List<T> executeQueryList(String sql, CustomRowMapper<?, T> mapper, Object... params) throws QueryExecutionException {
        validateInputs(sql, mapper, params);
        QueryLogger.logDebug("Executing query: " + sql + " | Params: " + Arrays.toString(params));

        List<T> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = getPreparedStatement(sql); ResultSet resultSet = executeQuery(preparedStatement, params)) {
            while (resultSet.next()) {
                mapRowToResult(results, mapper, resultSet, sql, params);
            }
        } catch (SQLException | DatabaseConnectionException e) {
            handleQueryExecutionError(sql, params, e);
        }

        return results;
    }

    private ResultSet executeQuery(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        setParameters(preparedStatement, params);
        return preparedStatement.executeQuery();
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

    private void handleQueryExecutionError(String sql, Object[] params, Exception e) throws QueryExecutionException {
        String errorMessage = String.format(
                "Failed to execute query: %s | Params: %s | Error: %s",
                sql, Arrays.toString(params), e.getMessage()
        );
        QueryLogger.logError(errorMessage, e);
        throw new QueryExecutionException(errorMessage, e);
    }

    private <T> void mapRowToResult(List<T> results, CustomRowMapper<?, T> mapper, ResultSet resultSet, String sql, Object[] params) throws QueryExecutionException {
        try {
            results.add(mapper.mapRow(resultSet));
        } catch (DataMappingException | SQLException e) {
            handleDataMappingError(sql, params, e);
        }
    }

    private void handleDataMappingError(String sql, Object[] params, Exception e) throws QueryExecutionException {
        String errorMessage = String.format(
                "Failed to map row for query: %s | Params: %s | Error: %s",
                sql, Arrays.toString(params), e.getMessage()
        );
        QueryLogger.logError(errorMessage, e);
        throw new QueryExecutionException(errorMessage, e);
    }
}
