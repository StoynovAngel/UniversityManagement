package interfaces;

import entity.Grade;

import java.util.List;

public interface GradeRepository {
    Grade getGradeByName(String name);
    List<Grade> getGradeByStudentName(String studentName);
    void updateGradeMark(double mark, Long id);
}
