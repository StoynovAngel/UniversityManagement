package com.angel.uni.management.services;

import com.angel.uni.management.entity.Student;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.StudentRepository;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.Optional;

public class StudentService implements StudentRepository {
    private final QueryManager queryManager;

    public StudentService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return queryManager.selectQuery().getStudentById(id, Mappers.getStudentMapper());
    }

    @Override
    public Optional<Student> getStudentByUsername(String username) {
        return queryManager.selectQuery().getStudentByUsername(username, Mappers.getStudentMapper());
    }

    @Override
    public void updateStudentUsername(String username, Long id) {
        queryManager.updateQuery().updateStudentUsername(username, id);
    }

    @Override
    public void insertStudent(String username) {
        queryManager.insertQuery().insertStudent(username);
    }
}
