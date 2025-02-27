package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeRepository {
    Grade getGradeByName(String name);
    Optional<List<Grade>> getGradesByStudentName(String studentName);
    void updateGradeMark(double mark, Long id);
    void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType);
}
