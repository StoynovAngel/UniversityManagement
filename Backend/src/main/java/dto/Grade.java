package dto;

import utils.exceptions.InvalidUserInput;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Grade implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String subject;
    private double mark;
    private final LocalDate dateOfGrading;

    public Grade(String subject, double mark) {
        this.subject = subject;
        this.mark = mark;
        this.dateOfGrading = LocalDate.now();
        gradeValidation(mark);
    }

    public String getSubject() {
        return subject;
    }

    public double getMark() {
        return mark;
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

    @Override
    public String toString() {
        return "Grade{" +
                "subject='" + subject + '\'' +
                ", mark=" + mark +
                ", dateOfGrading=" + dateOfGrading +
                '}';
    }
}
