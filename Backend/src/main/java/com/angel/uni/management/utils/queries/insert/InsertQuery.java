package com.angel.uni.management.utils.queries.insert;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.utils.exceptions.DatabaseConnectionException;
import com.angel.uni.management.utils.exceptions.QueryExecutionException;
import com.angel.uni.management.utils.queries.QueryUpdater;

import java.util.Optional;

/**
 * The class provides methods for executing INSERT operations in the database.
 * <p>
 * This class extends {@link QueryUpdater} and provides a method to insert by different parameters.
 * If the insertion fails, an error is logged.
 * </p>
 */

public class InsertQuery extends QueryUpdater {

    public void insertTeacher(String name) {
        updateAndHandleExceptions(InsertStatements.insertTeacher(), name);
    }

    public void insertSubject(String name, int hours_per_week, String teacherName, String description) {
        updateAndHandleExceptions(InsertStatements.insertSubject(), name, hours_per_week, teacherName, description);
    }

    public void insertStudent(String username) {
        updateAndHandleExceptions(InsertStatements.insertStudent(), username);
    }

    public void insertGroup(String name) {
        updateAndHandleExceptions(InsertStatements.insertGroup(), name);
    }

    public void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType) {
        updateAndHandleExceptions(InsertStatements.insertGrade(), name, mark, teacherName, studentUsername, gradeType);
    }

    public void insertStudentIntoGroup(String groupName, String studentUsername) {
        updateAndHandleExceptions(InsertStatements.insertStudentIntoGroup(), groupName, studentUsername);
    }

    public void insertStudentIntoSubject(String subjectName, String studentUsername) {
        updateAndHandleExceptions(InsertStatements.insertStudentIntoSubject(), subjectName, studentUsername);
    }
}
