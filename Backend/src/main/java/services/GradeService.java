package services;

import entity.Grade;
import interfaces.GradeRepository;
import utils.mappers.Mappers;
import java.util.List;

public class GradeService extends BasicService implements GradeRepository {

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
