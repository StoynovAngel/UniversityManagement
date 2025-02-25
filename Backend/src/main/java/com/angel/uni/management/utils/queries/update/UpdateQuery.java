package com.angel.uni.management.utils.queries.update;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.utils.exceptions.DataUpdateException;
import com.angel.uni.management.utils.queries.QueryUpdater;

/**
 * The class provides methods for executing INSERT operations in the database.
 * <p>
 * This class extends {@link QueryUpdater} and provides a method to insert by different parameters.
 * If the insertion fails, an error is logged, and a {@link DataUpdateException} is thrown.
 * </p>
 */

public class UpdateQuery extends QueryUpdater {

    public void updateTeacherName(String name, Long id) {
        try {
            updateSingleRow(UpdateStatements.updateTeacherNameById(), name, id);
        } catch (Exception e) {
            QueryLogger.logError(String.format("Failed to update teacher's name to '%s' for id: %d", name, id), e);
            throw new DataUpdateException(String.format("Update failed for teacher's id: %d with name: '%s'", id, name), e);
        }
    }

    public void updateSubjectDescription(String description, String name) {
        try {
            updateSingleRow(UpdateStatements.updateSubjectDescriptionBySubjectName(), description, name);
        } catch (Exception e) {
            QueryLogger.logError(String.format(
                    "Failed to update description: '%s' for subject: '%s'", description, name), e);
            throw new DataUpdateException(String.format(
                    "Update failed: Invalid subject name: '%s'", name), e);
        }
    }

    public void updateGradeMark(double mark, Long id) {
        try {
            updateSingleRow(UpdateStatements.updateGradeByStudentId(), mark, id);
        } catch (Exception e) {
            QueryLogger.logError(String.format("Failed to update grade mark to '%.2f' for student id: %d", mark, id), e);
            throw new DataUpdateException(String.format("Update failed for student id: %d with mark: %.2f", id, mark), e);
        }
    }

    public void updateStudentUsername(String username, Long id) {
        try {
            updateSingleRow(UpdateStatements.updateStudentUsernameById(), username, id);
        } catch (Exception e) {
            QueryLogger.logError(String.format("Failed to update username to '%s' for student id: %d", username, id), e);
            throw new DataUpdateException(String.format("Update failed for student id: %d with username: '%s'", id, username), e);
        }
    }

    public void updateGroupName(String name, Long id) {
        try {
            updateSingleRow(UpdateStatements.updateGroupNameById(), name, id);
        } catch (Exception e) {
            QueryLogger.logError(String.format("Failed to update group name to '%s' for group id: %d", name, id), e);
            throw new DataUpdateException(String.format("Update failed for group id: %d with name: '%s'", id, name), e);
        }
    }
}
