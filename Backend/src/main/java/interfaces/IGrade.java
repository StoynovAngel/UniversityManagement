package interfaces;

import dto.Grade;
import dto.Group;
import dto.User;

import java.util.List;

public interface IGrade {
    Grade addGradeToUser();
    void updateGrade(Grade grade);
    void addGrade(List<Grade> grades);
}
