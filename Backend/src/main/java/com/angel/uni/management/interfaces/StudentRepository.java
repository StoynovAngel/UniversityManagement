package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Student;

public interface StudentRepository {
    Student getStudentById(Long id);
    Student getStudentByUsername(String username);
    void updateStudentUsername(String username, Long id);
    void insertStudent(String username);
}
