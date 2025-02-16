package services;
import entity.Subject;
import interfaces.SubjectRepository;
import utils.mappers.Mappers;

public class SubjectService extends BasicService implements SubjectRepository {
    @Override
    public Subject getSubjectById(Long id) {
        return selectQuery.getSubjectById(id, Mappers.getSubjectMapper());
    }

    @Override
    public Subject getSubjectByName(String name) {
        return selectQuery.getSubjectByName(name, Mappers.getSubjectMapper());
    }

    @Override
    public void updateSubjectDescriptionBySubjectName(String description, String name) {
        updateQuery.updateSubjectDescription(description, name);
    }

    @Override
    public void deleteSubject(Long id) {
        deleteQuery.deleteSubjectById(id);
    }

    @Override
    public void insertSubject(String name, int hours_per_week, String teacherName, String description) {
        insertQuery.insertSubject(name, hours_per_week, teacherName, description);
    }

    @Override
    public void insertStudentIntoSubject(String subjectName, String studentUsername) {
        insertQuery.insertStudentIntoSubject(subjectName, studentUsername);
    }
}
