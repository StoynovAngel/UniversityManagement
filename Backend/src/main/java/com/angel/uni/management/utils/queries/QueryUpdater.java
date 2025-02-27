package com.angel.uni.management.utils.queries;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.utils.exceptions.DataUpdateException;
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

    public void updateSingleRow(String sql, Object... params) {
        try {
            QueryValidator.inputValidator(params);
            try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
                setParameters(preparedStatement, params);
                executeStatement(preparedStatement);
                System.out.println("Update successful.");
            }
        } catch (IllegalArgumentException e) {
            QueryLogger.logError("Invalid input parameters detected.", e);
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            QueryLogger.logError("SQL error while updating row. Query: " + sql, e);
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}
