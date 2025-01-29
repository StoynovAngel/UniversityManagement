package utils.queries.insert;

import enums.GradeType;
import utils.queries.QueryUpdater;

public class InsertQuery extends QueryUpdater {
    public void insertTeacher(String name) {
        updateSingleRow(InsertStatements.insertTeacher(), name);
    }

    public void insertSubject(String name, int hours_per_week, String teacherName, String description) {
        updateSingleRow(InsertStatements.insertSubject(), name, hours_per_week, teacherName, description);
    }

    public void insertStudent(String username) {
        updateSingleRow(InsertStatements.insertStudent(), username);
    }

    public void insertGroup(String name) {
        updateSingleRow(InsertStatements.insertGroup(), name);
    }

    public void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType) {
        updateSingleRow(InsertStatements.insertGrade(), name, mark, teacherName, studentUsername, gradeType);
    }
}
