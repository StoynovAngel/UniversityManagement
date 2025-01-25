package utils.queries;

import interfaces.CustomRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor extends BaseQuery{

    public <T> T executeSelect(String sql, CustomRowMapper<?, T> mapper, Object... params) {
        List<T> results = executeQueryList(sql, mapper, params);
        return results.isEmpty() ? null : results.get(0);
    }

    public <T> List<T> executeQueryList(String sql, CustomRowMapper<?, T> mapper, Object... params) {
        List<T> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            setParameters(preparedStatement, params);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(mapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query: " + sql, e);
        }
        return results;
    }


    private void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }

}
