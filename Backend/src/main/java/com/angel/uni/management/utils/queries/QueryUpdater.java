package com.angel.uni.management.utils.queries;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.utils.exceptions.DatabasePropertiesException;
import com.angel.uni.management.utils.exceptions.QueryExecutionException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Provides functionality for executing UPDATE/DELETE/INSERT statements.
 * <p>
 * This class extends {@link BaseQuery} and includes a method for updating a single row in the database using a parameterized SQL query.
 * </p>
 */

public class QueryUpdater extends BaseQuery {

    protected void updateAndHandleExceptions(String sql, Object... params) {
        try {
            updateSingleRow(sql, params);
        } catch (IllegalArgumentException e) {
            QueryLogger.logError("Illegal argument: " + e.getMessage());
            System.err.println("Update unsuccessful.");
        } catch (QueryExecutionException e) {
            QueryLogger.logError("updateSingleException", sql, params, e);
            System.err.println("Update unsuccessful.");
        } catch (DatabasePropertiesException e) {
            QueryLogger.logError("Connection failed to be established. Message: " + e.getMessage());
            System.err.println("Update unsuccessful.");
        }
    }

    private void updateSingleRow(String sql, Object... params) throws DatabasePropertiesException, QueryExecutionException {
        QueryValidator.inputValidator(params);
        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            setParameters(preparedStatement, params);
            executeStatement(preparedStatement);
            System.out.println("Update successful.");
        } catch (SQLException e) {
            String errorMessage = "Failed to update query: " + sql + " | Params: " + Arrays.toString(params);
            QueryLogger.logError(errorMessage, e);
            throw new QueryExecutionException(errorMessage, e);
        }
    }
}
