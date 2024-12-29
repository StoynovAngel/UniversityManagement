package utils.mappers;

import entity.Subject;
import interfaces.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements CustomRowMapper<Subject> {
    @Override
    public Subject mapRow(ResultSet resultSet) throws SQLException {
        return new Subject(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("hours_per_week"),
                resultSet.getString("description")
        );
    }
}
