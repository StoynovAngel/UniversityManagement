package dto;

import enums.GradeType;
import utils.exceptions.InvalidUserInput;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Grade implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String subject;
    private Teacher teacher;
    private GradeType gradeType;
    private double mark;
    private final LocalDate dateOfGrading;

    public Grade(String subject, double mark, Teacher teacher, GradeType gradeType) {
        this.mark = mark;
        this.subject = subject;
        this.teacher = teacher;
        this.gradeType = gradeType;
        this.dateOfGrading = LocalDate.now();
        gradeValidation(mark);
        typeValidation(gradeType);
    }

    public String getSubject() {
        return subject;
    }

    public GradeType getGradeType() {
        return gradeType;
    }

    public LocalDate getDateOfGrading() {
        return dateOfGrading;
    }

    public double getMark() {
        return mark;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setMark(double mark) {
        gradeValidation(mark);
        this.mark = mark;
    }

    protected void gradeValidation(double mark) {
        if (mark < 2 || mark > 6) {
            throw new InvalidUserInput("Mark must be between 2 and 6");
        }
    }

    protected void typeValidation(GradeType gradeType) {
        if (gradeType == null) { throw new NullPointerException(); }
    }

    @Override
    public String toString() {
        return "Grade{" +
                "subject='" + subject + '\'' +
                ", mark=" + mark +
                ", dateOfGrading=" + dateOfGrading +
                '}';
    }
}
