package interfaces;

import dto.Subject;
import enums.GradeType;

import java.time.LocalDate;

public interface IGrade {
    void updateGrade(Subject subject);
    void deleteGrade(Subject subject);
    void viewGradesBasedOnDate(Subject subject, LocalDate date);
    GradeType getGradeType();
}
