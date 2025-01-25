package interfaces;

import entity.Subject;

public interface SubjectRepository {
    Subject getSubjectById(Long id);
}
