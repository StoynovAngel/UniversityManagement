package utils.queries.insert;

import entity.Student;
import entity.Subject;
import utils.queries.BaseQuery;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertQuery extends BaseQuery {

    public InsertQuery() {
        super();
    }

    public void saveStudent(Student student) {
        insertStudent(student);
    }

    public void saveSubject(Subject subject) {
        insertSubject(subject);
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

    private void insertSubject(Subject subject) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(InsertStatements.insertSubjectSql())) {
            prepareSubjectStatement(preparedStatement, subject);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareSubjectStatement(PreparedStatement preparedStatement, Subject subject) throws SQLException {
        preparedStatement.setLong(1, subject.getId());
        preparedStatement.setString(2, subject.getName());
        preparedStatement.setInt(3, subject.getHoursPerWeek());
        preparedStatement.setString(4, subject.getDescription());
        executeStatement(preparedStatement);
    }
}
