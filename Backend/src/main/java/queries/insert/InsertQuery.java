package queries.insert;

import entity.Student;
import queries.BaseQuery;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertQuery extends BaseQuery {

    public InsertQuery() {
        super();
    }

    public void saveStudent(Student student) {
        insertStudent(student);
    }

    private void insertStudent(Student student) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(InsertStatements.insertStudentSql())) {
            prepareStudentStatement(preparedStatement, student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareStudentStatement(PreparedStatement preparedStatement, Student student) throws SQLException {
        preparedStatement.setLong(1, student.getId());
        preparedStatement.setString(2, student.getUsername());
        preparedStatement.setDouble(3, student.getAverageGradePerSubject());
        preparedStatement.setDouble(4, student.getAverageGradeOverall());
        executeStatement(preparedStatement);
    }
}
