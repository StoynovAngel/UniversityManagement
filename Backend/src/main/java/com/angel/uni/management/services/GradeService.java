package com.angel.uni.management.services;

import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.interfaces.GradeRepository;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.utils.mappers.Mappers;
import java.util.List;
import java.util.Optional;

public class GradeService implements GradeRepository {
    private final QueryManager queryManager;

    public GradeService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public Grade getGradeByName(String name) {
        return queryManager.selectQuery().getGradeByName(name, Mappers.getGradeMapper());
    }

    @Override
    public Optional<List<Grade>> getGradesByStudentName(String studentName) {
        return queryManager.selectQuery().getGradesByStudentName(studentName, Mappers.getGradeMapper());
    }

    @Override
    public void updateGradeMark(double mark, Long id) {
        queryManager.updateQuery().updateGradeMark(mark, id);
    }

    @Override
    public void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType) {
        queryManager.insertQuery().insertGrade(name, mark, teacherName, studentUsername, gradeType);
    }
}
