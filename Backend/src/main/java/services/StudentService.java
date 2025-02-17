package services;

import entity.Student;
import interfaces.StudentRepository;
import utils.mappers.Mappers;

public class StudentService implements StudentRepository {

    @Override
    public Student getStudentById(Long id) {
        return QueryManager.getSelectQuery().getStudentById(id, Mappers.getStudentMapper());
    }

    @Override
    public Student getStudentByUsername(String username) {
        return QueryManager.getSelectQuery().getStudentByUsername(username, Mappers.getStudentMapper());
    }

    @Override
    public void updateStudentUsername(String username, Long id) {
        QueryManager.getUpdateQuery().updateStudentUsername(username, id);
    }

    @Override
    public void insertStudent(String username) {
        QueryManager.getInsertQuery().insertStudent(username);
    }
}
