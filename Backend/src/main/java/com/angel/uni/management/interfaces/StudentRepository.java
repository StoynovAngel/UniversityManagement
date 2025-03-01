package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Student;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> getStudentById(Long id);
    Optional<Student> getStudentByUsername(String username);
    void updateStudentUsername(String username, Long id);
    void insertStudent(String username);
}
