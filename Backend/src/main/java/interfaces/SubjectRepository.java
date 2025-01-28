package interfaces;

import entity.Subject;

public interface SubjectRepository {
    Subject getSubjectById(Long id);
    Subject getSubjectByName(String name);
    void updateSubjectDescriptionBySubjectName(String description, String name);
}
