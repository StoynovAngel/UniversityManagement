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

    @Override
    public Student getStudentByUsername(String username) {
        return selectQuery.getStudentByUsername(username, studentMapper);
    }

    @Override
    public void updateStudentUsername(String username, Long id) {
        updateQuery.updateStudentUsername(username, id);
    }

    @Override
    public void insertStudent(String username) {
        insertQuery.insertStudent(username);
    }
}
