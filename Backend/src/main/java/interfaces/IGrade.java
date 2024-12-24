package interfaces;

import dto.Subject;
import enums.GradeType;

public interface IGrade {
    void updateGrade(Subject subject);
    void deleteGrade(Subject subject);
    GradeType getGradeType();
}
