package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Grade;
import java.util.List;

public interface GradeRepository {
    Grade getGradeByName(String name);
    List<Grade> getGradesByStudentName(String studentName);
    void updateGradeMark(double mark, Long id);
    void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType);
}
