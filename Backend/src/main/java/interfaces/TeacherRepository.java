package interfaces;

import entity.Teacher;

public interface TeacherRepository {
    Teacher getTeacherById(Long id);
}
