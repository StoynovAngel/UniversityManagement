package services;

import dto.User;
import handlers.GradeHandler;
import interfaces.IGrade;

public class GradeService implements IGrade {
    private final GradeHandler gradeHandler;

    public GradeService(GradeHandler gradeHandler) {
        this.gradeHandler = gradeHandler;
    }

    @Override
    public void addGrade(User user) {
        gradeHandler.addGradeToUser(user);
    }

    @Override
    public void updateGrade(User user) {
        gradeHandler.updateUserGrade(user);
    }

    @Override
    public void deleteGrade(User user) {
        gradeHandler.deleteGrade(user);
    }
}
