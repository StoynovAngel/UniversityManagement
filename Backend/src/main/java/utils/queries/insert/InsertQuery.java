package utils.queries.insert;

import config.QueryLogger;
import utils.exceptions.DataUpdateException;
import utils.queries.QueryUpdater;

/**
 * The class provides methods for executing INSERT operations in the database.
 * <p>
 * This class extends {@link QueryUpdater} and provides a method to insert by different parameters.
 * If the insertion fails, an error is logged, and a {@link DataUpdateException} is thrown.
 * </p>
 */

public class InsertQuery extends QueryUpdater {

    public void insertTeacher(String name) {
        try {
            updateSingleRow(InsertStatements.insertTeacher(), name);
        } catch (Exception e) {
            QueryLogger.logError("Failed to insert teacher with name " + name, e);
            throw new DataUpdateException("Insertion failed: Invalid teacher name: " + name, e);
        }
    }

    public void insertSubject(String name, int hours_per_week, String teacherName, String description) {
        try {
            updateSingleRow(InsertStatements.insertSubject(), name, hours_per_week, teacherName, description);
        } catch (Exception e) {
            String formattedParams = String.format("name='%s', hours_per_week=%d, teacherName='%s', description='%s'",
                    name, hours_per_week, teacherName, description);

            QueryLogger.logError("Failed to insert subject with parameters: " + formattedParams, e);
            throw new DataUpdateException("Insertion failed: Could not insert subject with given details: " + formattedParams, e);
        }
    }

    public void insertStudent(String username) {
        try {
            updateSingleRow(InsertStatements.insertStudent(), username);
        } catch (Exception e) {
            QueryLogger.logError("Failed to insert student with username " + username, e);
            throw new DataUpdateException("Insertion failed: Invalid student username: " + username, e);
        }
    }

    public void insertGroup(String name) {
        try {
            updateSingleRow(InsertStatements.insertGroup(), name);
        } catch (Exception e) {
            QueryLogger.logError("Failed to insert teacher with name " + name, e);
            throw new DataUpdateException("Insertion failed: Invalid group name: " + name, e);
        }
    }

    public void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType) {
        try {
            updateSingleRow(InsertStatements.insertGrade(), name, mark, teacherName, studentUsername, gradeType);
        } catch (Exception e) {
            String formattedParams = String.format("name='%s', mark=%.2f, teacherName='%s', studentUsername='%s' gradeType='%s'",
                    name, mark, teacherName, studentUsername, gradeType);

            QueryLogger.logError("Failed to insert Grade with parameters: " + formattedParams, e);
            throw new DataUpdateException("Insertion failed: Could not insert grade with given details: " + formattedParams, e);
        }
    }

    public void insertStudentIntoGroup(String groupName, String studentUsername) {
        try {
            updateSingleRow(InsertStatements.insertStudentIntoGroup(), groupName, studentUsername);
        } catch (Exception e) {
            QueryLogger.logError("Failed to insert Student: " + studentUsername + " into group which has this group name: " + groupName, e);
            throw new DataUpdateException("Insertion failed: Could not insert Student: " + studentUsername + " into group:" + groupName, e);
        }
    }

    public void insertStudentIntoSubject(String subjectName, String studentUsername) {
        try {
            updateSingleRow(InsertStatements.insertStudentIntoSubject(), subjectName, studentUsername);
        } catch (Exception e) {
            QueryLogger.logError("Failed to insert Student: " + studentUsername + " into subject: " + subjectName, e);
            throw new DataUpdateException("Insertion failed: Could not insert Student: " + studentUsername + " into subject:" + subjectName, e);
        }
    }

}
