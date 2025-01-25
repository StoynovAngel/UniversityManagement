package services;

import entity.Student;
import interfaces.StudentRepository;
import utils.mappers.StudentMapper;

public class StudentService extends BasicService implements StudentRepository {
    private final StudentMapper studentMapper = new StudentMapper();

    @Override
    public Student getStudentById(Long id) {
        return selectQuery.getStudentById(id, studentMapper);
    }
}
