package entity;

import enums.GradeType;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "grade")
public class GradeBG extends Grade{
    public GradeBG(String name, Student student, Teacher teacher, GradeType gradeType, double mark, Date dateOfGrading) {
        super(name, student, teacher, gradeType, mark, dateOfGrading);
        setMark(mark);
    }

    public GradeBG() {
        super();
    }

    @Override
    protected void validateMark(double mark) {
        if (mark < 2.0 || mark > 6.0) {
            throw new IllegalArgumentException("BG Grades must be between 0.0 and 6.0");
        }
    }
}
