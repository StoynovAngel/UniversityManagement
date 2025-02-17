package services;
import entity.Subject;
import interfaces.SubjectRepository;
import utils.mappers.Mappers;

public class SubjectService implements SubjectRepository {
    @Override
    public Subject getSubjectById(Long id) {
        return QueryManager.getSelectQuery().getSubjectById(id, Mappers.getSubjectMapper());
    }

    @Override
    public Subject getSubjectByName(String name) {
        return QueryManager.getSelectQuery().getSubjectByName(name, Mappers.getSubjectMapper());
    }

    @Override
    public void updateSubjectDescriptionBySubjectName(String description, String name) {
        QueryManager.getUpdateQuery().updateSubjectDescription(description, name);
    }

    @Override
    public void deleteSubject(Long id) {
        QueryManager.getDeleteQuery().deleteSubjectById(id);
    }

    @Override
    public void insertSubject(String name, int hours_per_week, String teacherName, String description) {
        QueryManager.getInsertQuery().insertSubject(name, hours_per_week, teacherName, description);
    }

    @Override
    public void insertStudentIntoSubject(String subjectName, String studentUsername) {
        QueryManager.getInsertQuery().insertStudentIntoSubject(subjectName, studentUsername);
    }
}
