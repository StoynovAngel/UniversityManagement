package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository {
    Optional<Teacher> getTeacherById(Long id);
    void updateTeacherName(String name, Long id);
    void insertTeacher(String name);
}
