package utils.queries.delete;

import entity.Student;
import utils.queries.BaseQuery;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteQuery extends BaseQuery {
    public DeleteQuery() {
        super();
    }

    public void removeStudent(Student student) {
        deleteStudent(student);
    }

    private void deleteStudent(Student student) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DeleteStatements.deleteStudentSql())) {
            prepareStudentIdStatement(preparedStatement, student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareStudentIdStatement(PreparedStatement preparedStatement, Student student) throws SQLException {
        preparedStatement.setLong(1, student.getId());
        executeStatement(preparedStatement);
    }

}
