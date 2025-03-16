package com.angel.uni.management.utils.queries.update;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.utils.queries.QueryUpdater;

/**
 * The class provides methods for executing INSERT operations in the database.
 * <p>
 * This class extends {@link QueryUpdater} and provides a method to insert by different parameters.
 * If the insertion fails, an error is logged.
 * </p>
 */

public class UpdateQuery extends QueryUpdater {

    public void updateTeacherName(String name, Long id) {
        updateAndHandleExceptions(UpdateStatements.updateTeacherNameById(), name, id);
    }

    public void updateSubjectDescription(String description, String name) {
        updateAndHandleExceptions(UpdateStatements.updateSubjectDescriptionBySubjectName(), description, name);
    }

    public void updateGradeMark(double mark, Long id) {
        updateAndHandleExceptions(UpdateStatements.updateGradeByStudentId(), mark, id);
    }

    public void updateStudentUsername(String username, Long id) {
        updateAndHandleExceptions(UpdateStatements.updateStudentUsernameById(), username, id);
    }

    public void updateGroupName(String name, Long id) {
        updateAndHandleExceptions(UpdateStatements.updateGroupNameById(), name, id);
    }
}
