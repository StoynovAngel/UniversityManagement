package utils.queries.select;

import interfaces.CustomRowMapper;
import utils.queries.BaseQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectQuery extends BaseQuery {
    public SelectQuery() {
        super();
    }

    public <T> T getById(Long id, CustomRowMapper<T> mapper) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SelectStatements.selectSubjectByIdSql())) {
            preparedStatement.setLong(1, id);
            return getAttributesFromColumns(preparedStatement, mapper);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch record: " + e.getMessage(), e);
        }
    }

    private <T> T getAttributesFromColumns(PreparedStatement preparedStatement, CustomRowMapper<T> mapper) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return mapper.mapRow(resultSet);
        }
        return null;
    }
}