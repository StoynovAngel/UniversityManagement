package utils.queries.update;

import utils.queries.QueryUpdater;

public class UpdateQuery extends QueryUpdater {
    public void updateTeacherName(String name, Long id) {
        updateSingleRow(UpdateStatements.updateTeacherNameById(), name, id);
    }

    public void updateSubjectDescription(String description, String name) {
        updateSingleRow(UpdateStatements.updateSubjectDescriptionBySubjectName(), description, name);
    }

    public void updateGradeMark(double mark, Long id) {
        updateSingleRow(UpdateStatements.updateGradeByStudentId(), mark, id);
    }

    public void updateStudentUsername(String username, Long id) {
        updateSingleRow(UpdateStatements.updateStudentUsernameById(), username, id);
    }

    public void updateGroupName(String name, Long id) {
        updateSingleRow(UpdateStatements.updateGroupNameById(), name, id);
    }
}
