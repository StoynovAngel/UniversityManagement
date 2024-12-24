package interfaces;

import dto.Group;
import dto.Student;

public interface IStudent {
    void viewGrades(Student student);
    void viewSubjects(Student student);
    double calculateOverallGrade(Student student);
    double calculateGradeForSubject(String subjectName, Student student);
    void enrollInGroup(Student student, Group group);
}
