package queries.select;

import entity.Student;
import queries.BaseQuery;
import queries.insert.InsertStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectQuery extends BaseQuery {
    public SelectQuery() {
        super();
    }

    public List<Student> getAllStudents() {
        ResultSet resultSet = executeSelectAllStudents();
        return mapStudents(resultSet);
    }

    private ResultSet executeSelectAllStudents() {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(SelectStatements.selectAllStudentsSql());
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute SELECT query: " + e.getMessage(), e);
        }
    }

    private List<Student> mapStudents(ResultSet resultSet) {
        List<Student> students = new ArrayList<>();
        try {
            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to map students: " + e.getMessage(), e);
        }
        return students;
    }

    private Student mapResultSetToStudent(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        double averageGradePerSubject = resultSet.getDouble("average_grade_per_subject");
        double averageGradeOverall = resultSet.getDouble("average_grade_overall");
        return new Student(id, username, new ArrayList<>(), averageGradePerSubject, averageGradeOverall);
    }
}