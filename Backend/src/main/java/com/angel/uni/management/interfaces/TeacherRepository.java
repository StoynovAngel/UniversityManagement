package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Teacher;

public interface TeacherRepository {
    Teacher getTeacherById(Long id);
    void updateTeacherName(String name, Long id);
    void insertTeacher(String name);
}
