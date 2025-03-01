package com.angel.uni.management.services;

import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.TeacherRepository;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.Optional;

public class TeacherService implements TeacherRepository {
    private final QueryManager queryManager;

    public TeacherService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return queryManager.selectQuery().getTeacherById(id, Mappers.getTeacherMapper());
    }

    @Override
    public void updateTeacherName(String name, Long id) {
        queryManager.updateQuery().updateTeacherName(name, id);
    }

    @Override
    public void insertTeacher(String name) {
        queryManager.insertQuery().insertTeacher(name);
    }
}
