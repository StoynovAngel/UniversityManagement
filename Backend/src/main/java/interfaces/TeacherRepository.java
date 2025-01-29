package interfaces;

import entity.Teacher;

public interface TeacherRepository {
    Teacher getTeacherById(Long id);
    void updateTeacherName(String name, Long id);
    void insertTeacher(String name);
}
