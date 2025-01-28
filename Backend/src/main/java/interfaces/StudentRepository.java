package interfaces;

import entity.Student;

public interface StudentRepository {
    Student getStudentById(Long id);
    Student getStudentByUsername(String username);
    void updateStudentUsername(String username, Long id);
}
