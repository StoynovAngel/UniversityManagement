package services;
;
import entity.Subject;
import interfaces.SubjectRepository;
import utils.mappers.SubjectMapper;

public class SubjectService extends BasicService implements SubjectRepository {
    private final SubjectMapper subjectMapper = new SubjectMapper();
    @Override
    public Subject getSubjectById(Long id) {
        return selectQuery.getSubjectById(id, subjectMapper);
    }
}
