package services;
import entity.Subject;
import interfaces.SubjectRepository;
import utils.mappers.SubjectMapper;

public class SubjectService extends BasicService implements SubjectRepository {
    private final SubjectMapper subjectMapper = new SubjectMapper();
    @Override
    public Subject getSubjectById(Long id) {
        return selectQuery.getSubjectById(id, subjectMapper);
    }

    @Override
    public Subject getSubjectByName(String name) {
        return selectQuery.getSubjectByName(name, subjectMapper);
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
}
