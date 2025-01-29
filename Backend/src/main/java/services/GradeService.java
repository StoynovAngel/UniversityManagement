package services;

import entity.Grade;
import enums.GradeType;
import interfaces.GradeRepository;
import utils.mappers.GradeMapper;

import java.util.List;

public class GradeService extends BasicService implements GradeRepository {
    private final GradeMapper gradeMapper = new GradeMapper();

    @Override
    public Grade getGradeByName(String name) {
        return selectQuery.getGradeByName(name, gradeMapper);
    }

    @Override
    public List<Grade> getGradeByStudentName(String studentName) {
        return selectQuery.getGradeByStudentName(studentName, gradeMapper);
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
