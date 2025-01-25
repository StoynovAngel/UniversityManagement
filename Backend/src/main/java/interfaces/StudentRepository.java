package interfaces;

import entity.Student;

public interface StudentRepository {
    Student getStudentById(Long id);
}
