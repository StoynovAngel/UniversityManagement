package interfaces;

import dto.*;

public interface ITeacher {
    Subject createSubject();
    void addGradeToSubject(Subject subject);

}
