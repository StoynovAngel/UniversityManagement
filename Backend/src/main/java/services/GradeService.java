package services;

import entity.Grade;
import interfaces.GradeRepository;
import utils.mappers.Mappers;
import java.util.List;

public class GradeService implements GradeRepository {

    @Override
    public Grade getGradeByName(String name) {
        return QueryManager.getSelectQuery().getGradeByName(name, Mappers.getGradeMapper());
    }

    @Override
    public List<Grade> getGradesByStudentName(String studentName) {
        return QueryManager.getSelectQuery().getGradesByStudentName(studentName, Mappers.getGradeMapper());
    }

    @Override
    public void updateGradeMark(double mark, Long id) {
        QueryManager.getUpdateQuery().updateGradeMark(mark, id);
    }

    @Override
    public void insertGrade(String name, double mark, String teacherName, String studentUsername, String gradeType) {
        QueryManager.getInsertQuery().insertGrade(name, mark, teacherName, studentUsername, gradeType);
    }
}
