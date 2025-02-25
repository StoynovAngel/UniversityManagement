package com.angel.uni.management.services;

import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.interfaces.GradeRepository;
import com.angel.uni.management.utils.mappers.Mappers;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

import java.util.List;

public class GradeService implements GradeRepository {
    private final SelectQuery selectQuery = QueryManager.getSelectQuery();
    private final UpdateQuery updateQuery = QueryManager.getUpdateQuery();
    private final InsertQuery insertQuery = QueryManager.getInsertQuery();

    @Override
    public Grade getGradeByName(String name) {
        return selectQuery.getGradeByName(name, Mappers.getGradeMapper());
    }

    @Override
    public List<Grade> getGradesByStudentName(String studentName) {
        return selectQuery.getGradesByStudentName(studentName, Mappers.getGradeMapper());
    }

    @Override
    public void updateGradeMark(double mark, Long id) {
        updateQuery.updateGradeMark(mark, id);
    }

    @Override
    public void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType) {
        insertQuery.insertGrade(name, mark, teacherName, studentUsername, gradeType);
    }
}
