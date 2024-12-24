package services;

import dto.Student;
import dto.Subject;
import dto.User;
import enums.GradeType;
import utils.handlers.GradeHandler;
import interfaces.IGrade;

import java.time.LocalDate;

public class GradeService implements IGrade {
    private final GradeHandler gradeHandler;

    public GradeService(GradeHandler gradeHandler) {
        this.gradeHandler = gradeHandler;
    }

    @Override
    public void updateGrade(Subject subject) {
        gradeHandler.updateSubjectGrade(subject);
    }

    @Override
    public void deleteGrade(Subject subject) {
        gradeHandler.deleteGrade(subject);
    }

    @Override
    public void viewGradesBasedOnDate(Subject subject, LocalDate date) {
        gradeHandler.viewGradesBasedOnDate(subject, date);
    }

    @Override
    public GradeType getGradeType() {
        return gradeHandler.getGradeType();
    }
}
