package com.angel.uni.management.services;

import com.angel.uni.management.dto.simple.SimpleStudentDTO;
import com.angel.uni.management.dto.update.UpdateStudentDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.Optional;

public class StudentService implements Service<Student, UpdateStudentDTO, SimpleStudentDTO> {
    private final QueryManager queryManager;

    public StudentService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public void create(SimpleStudentDTO dto) {
        queryManager.insertQuery().insertStudent(dto.username());
    }

    @Override
    public Optional<Student> read(Long id) {
        return queryManager.selectQuery().getStudentById(id, Mappers.getStudentMapper());
    }

    @Override
    public Optional<Student> read(String name) {
        return queryManager.selectQuery().getStudentByUsername(name, Mappers.getStudentMapper());
    }

    @Override
    public void update(UpdateStudentDTO dto) {
        queryManager.updateQuery().updateStudentUsername(dto.username(), dto.id());
    }

    @Override
    public void delete(Long id) {
    }
}
