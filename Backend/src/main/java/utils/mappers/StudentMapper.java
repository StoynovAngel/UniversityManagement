package utils.mappers;

import entity.Student;
import interfaces.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements CustomRowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getLong("id"),
                resultSet.getString("username")
        );
    }
}
