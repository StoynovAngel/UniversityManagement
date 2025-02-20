package utils.queries;

import config.QueryLogger;
import interfaces.CustomRowMapper;
import utils.exceptions.DataAccessException;
import java.sql.*;
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

    public <T> T executeSelect(String sql, CustomRowMapper<?, T> mapper, Object... params) {
        QueryValidator.inputValidator(params);
        List<T> results = executeQueryList(sql, mapper, params);
        return results.isEmpty() ? null : results.get(0);
    }

    public <T> List<T> executeQueryList(String sql, CustomRowMapper<?, T> mapper, Object... params) {
        List<T> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            setParameters(preparedStatement, params);
            QueryLogger.logQuery(sql, params);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(mapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            QueryLogger.logError("SQL execution failed for query: " + sql + " Params: " + Arrays.toString(params), e);
            throw new DataAccessException("Database query execution failed", e);
        }
        return results;
    }
}
