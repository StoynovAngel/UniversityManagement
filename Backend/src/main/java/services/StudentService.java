package services;

import dto.Group;
import dto.Student;
import interfaces.IStudent;
import utils.handlers.StudentHandler;

public class StudentService implements IStudent {
    private StudentHandler studentHandler = new StudentHandler();

    @Override
    public void viewGrades(Student student) {
        studentHandler.viewGrades(student);
    }

    @Override
    public void viewSubjects(Student student) {
        studentHandler.viewSubjects(student);
    }

    @Override
    public double calculateOverallGrade(Student student) {
        return studentHandler.calculateOverallGrade(student);
    }

    @Override
    public double calculateGradeForSubject(String subjectName, Student student) {
        return studentHandler.calculateGradeForSubject(subjectName, student);
    }

    @Override
    public void enrollInGroup(Student student, Group group) {
        studentHandler.enrollInGroup(student, group);
    }
}
