package com.angel.uni.management.services;

import com.angel.uni.management.dto.simple.SimpleTeacherDTO;
import com.angel.uni.management.dto.update.UpdateTeacherDTO;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.Optional;

public class TeacherService implements Service<Teacher, UpdateTeacherDTO, SimpleTeacherDTO> {
    private final QueryManager queryManager;

    public TeacherService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }


    @Override
    public void create(SimpleTeacherDTO dto) {
        queryManager.insertQuery().insertTeacher(dto.teacherName());
    }

    @Override
    public Optional<Teacher> read(Long id) {
        return queryManager.selectQuery().getTeacherById(id, Mappers.getTeacherMapper());
    }

    @Override
    public Optional<Teacher> read(String name) {
        return queryManager.selectQuery().getTeacherByName(name, Mappers.getTeacherMapper());
    }

    @Override
    public void update(UpdateTeacherDTO dto) {
        queryManager.updateQuery().updateTeacherName(dto.name(), dto.id());
    }

    @Override
    public void delete(Long id) {

    }
}
