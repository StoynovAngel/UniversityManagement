package services;

import entity.Student;
import interfaces.StudentRepository;
import utils.mappers.Mappers;

public class StudentService extends BasicService implements StudentRepository {

    @Override
    public Student getStudentById(Long id) {
        return selectQuery.getStudentById(id, Mappers.getStudentMapper());
    }

    @Override
    public Student getStudentByUsername(String username) {
        return selectQuery.getStudentByUsername(username, Mappers.getStudentMapper());
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
