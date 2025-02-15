package entity;

import enums.GradeType;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "grade")
public class GradeBG extends Grade{
    private static final double MIN_MARK = 2.0;
    private static final double MAX_MARK = 6.0;

    public GradeBG(String name, Student student, Teacher teacher, GradeType gradeType, double mark, Date dateOfGrading) {
        super(name, student, teacher, gradeType, mark, dateOfGrading);
        setMark(mark);
    }

    @Override
    protected void validateMark(double mark) {
        if (mark < MIN_MARK || mark > MAX_MARK) {
            throw new IllegalArgumentException("BG Grades must be between 2.0 and 6.0");
        }
    }
}
