package interfaces;

import entity.Subject;

public interface SubjectRepository {
    Subject getSubjectById(Long id);
    Subject getSubjectByName(String name);
    void updateSubjectDescriptionBySubjectName(String description, String name);
    void deleteSubject(Long id);
    void insertSubject(String name, int hours_per_week, String teacherName, String description);
    void insertStudentIntoSubject(String subjectName, String studentUsername);
}
