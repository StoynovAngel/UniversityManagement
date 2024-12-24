package services;

import dto.Student;
import dto.Subject;
import dto.User;
import enums.GradeType;
import utils.handlers.GradeHandler;
import interfaces.IGrade;

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
    public GradeType getGradeType() {
        return gradeHandler.getGradeType();
    }
}
