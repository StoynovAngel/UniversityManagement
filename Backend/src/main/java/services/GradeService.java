package services;

import dto.Grade;
import dto.Group;
import dto.User;
import handlers.GradeHandler;
import interfaces.IGrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeService implements IGrade {
    private final GradeHandler gradeHandler;

    public GradeService(GradeHandler gradeHandler) {
        this.gradeHandler = gradeHandler;
    }

    @Override
    public Grade addGradeToUser() {
        return gradeHandler.addGradeToUser();
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeHandler.updateGrade(grade);
    }

    @Override
    public void addGrade(List<Grade> grades) {
        gradeHandler.addGrade(grades);
    }

}
